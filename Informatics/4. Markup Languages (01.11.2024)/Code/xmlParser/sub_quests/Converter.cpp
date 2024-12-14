//
// Created by User on 02.11.2024.
//

#include <string>
#include <utility>
#include "Converter.h"
#include <vector>
#include <fstream>
#include <sstream>
#include <regex>
#include <cassert>
#include <iostream>

std::string Converter::getOutput(Node* &v, int tabSize, bool inList = false) {
    std::string result;
    if (v->tagName == "for") {
        result += getTab(tabSize++) + putBrackets("for") + ": [\n";
        int l = getVariableValue(v->attributes["__leftEdge"]), r = getVariableValue(v->attributes["__rightEdge"]);
        for (int i = l; i <= r; i++) {
            args[v->attributes["__variableName"]] = i;
            result += getTab(tabSize++) + "{\n";
            result += Converter::ToChild(v, tabSize);
            result.pop_back();
            if (result.back() == ',') result.pop_back();
            result += "\n" + getTab(--tabSize) + "},\n";
        }
        tabSize--;
        result.pop_back();
        if (result.back() == ',') result.pop_back();
        result += "\n" + getTab(tabSize) + "]";
    } else {
        if (!inList) result += getTab(tabSize) + v->tagName + ": ";
        int sumSize = (int) v->attributes.size() + (int) v->childNodes.size();
        if (sumSize == 0) {
            result += putBrackets("");
            return result;
        }
        if (sumSize == 1 && (int) v->attributes.size() == 1 && v->attributes.find("__text") != v->attributes.end()) {
            result += replaceVariables(v->attributes["__text"]);
            return result;
        }
        if (inList)
            result += getTab(tabSize);
        result += "{\n";
        tabSize++;
        result += ToChild(v, tabSize);
        for (auto &att: v->attributes) {
            std::string attName = replaceVariables(att.first);
            std::string attValue = replaceVariables(att.second);
            result += getTab(tabSize) + putBrackets(attName) + ": " + attValue + ",\n";
        }
        tabSize--;
        result.pop_back();
        if (result.back() == ',') result.pop_back();
        result += "\n" + getTab(tabSize) + "}";
    }
  return result;
}

std::string Converter::replaceVariables(const std::string &line) {
    std::string s = line;
    for (const auto &[key,val] : args) {
        std::string variable = "${" + key + "}";
        size_t start {s.find(variable)};            // находим позицию подстроки
        while (start != std::string::npos) // находим и заменяем все вхождения строки old_str
        {
            s.replace(start, variable.length(), std::to_string(val)); // Замена old_str на new_str
            start = s.find(variable, start + std::to_string(val).length());
        }
    }
    return s;
}

std::string Converter::ToChild(Node* &v, int tabSize) {
    std::string result;
    for (auto& child: v->childNodes) {
        std::string childTag = child.first;
        std::vector<Node*> nodes = child.second;
        if ((int)nodes.size() > 1) {
            result += getTab(tabSize) + replaceVariables(nodes.back()->tagName) + ": [\n";
            tabSize++;
            for (auto &n : nodes) {
                result += getOutput(n, tabSize, true) + ",\n";
            }
            result.pop_back(); result.pop_back();
            tabSize--;
            result += "\n" + getTab(tabSize) + "],\n";
        } else {
            result += getOutput(nodes.back(), tabSize, false) + ",\n";
        }
    }
    return result;
}

int Converter::getVariableValue(std::string s) {
    int res;
    if (s[0] == '$') res = args[Converter::split(s,"${}")[0]];
    else res = stoi(s);
    return res;
}

void Converter::printJSON() {
  std::string out;
  std::cout << "{\n" << Converter::getOutput(this->root, 1) << "\n}";
}

void Converter::buildTree(Node* &v, int ind, const std::vector<std::string> &tags, std::vector<Node*> &openTags) {
  const std::regex singleTag(R"(<\w+\s*(\w+\s*=".*")*\s*\/>.*)");
  const std::regex openPairedTag(R"(<\w+\s*(\w+\s*=".*")*\s*>)");
  const std::regex closePairedTag(R"(<\/\w+\s*>)");
  const std::regex openCycle(R"(<\w+\s*\w+ := \S* to \S*>)");
  const std::regex closedCycle(R"(<end for>)");
  std::smatch match;
  if (std::regex_match(tags[ind], match, singleTag)) {  // Одинарный тег обрабатываем отдельно
    std::map<std::string, std::string> attributes = Converter::findAttributes(tags[ind]);
    Node* u = new Node(attributes["__tagName"]);
    attributes.erase("__tagName");
    u->attributes = attributes;
    if (v != nullptr) {
      v->childNodes[u->tagName].push_back(u);
      if (++ind < tags.size()) buildTree(v, ind, tags, openTags);
    } else {
      this->root = u;
      if (++ind < tags.size()) buildTree(u, ind, tags, openTags);
    }
  } else if (std::regex_match(tags[ind], match, openPairedTag)) {
    std::map<std::string, std::string> attributes = Converter::findAttributes(tags[ind]);
    Node* u = new Node(attributes["__tagName"]);
    attributes.erase("__tagName");
    u->attributes = attributes;
    openTags.push_back(u);
    if (v != nullptr) {
      v->childNodes[u->tagName].push_back(u);
    } else this->root = u;
    if (++ind < tags.size()) buildTree(u, ind, tags, openTags);
  } else if (std::regex_match(tags[ind], match, closePairedTag)) {
    assert(v->tagName == putBrackets(Converter::split(tags[ind], "</>")[0]));
    openTags.pop_back();
    if (++ind < tags.size()) {
      Node* par = openTags.back();
      buildTree(par, ind, tags, openTags);
    }
  } else if (std::regex_match(tags[ind], match, openCycle)){
      std::map<std::string, std::string> cycleAttributes = Converter::findCycleAttributes(tags[ind]);
      Node* u = new Node(cycleAttributes["__tagName"]);
      cycleAttributes.erase("__tagName");
      u->attributes = cycleAttributes;
      openTags.push_back(u);
      if (v != nullptr) {
          v->childNodes[u->tagName].push_back(u);
      } else this->root = u;
      if (++ind < tags.size()) buildTree(u, ind, tags, openTags);
  } else if (std::regex_match(tags[ind], match, closedCycle)) {
      openTags.pop_back();
      if (++ind < tags.size()) {
          Node* par = openTags.back();
          buildTree(par, ind, tags, openTags);
      }
  } else {
    if (v != nullptr) {
      v->attributes["__text"] = putBrackets(tags[ind]);
      if (++ind < tags.size()) {
        buildTree(v, ind, tags, openTags);
      }
    }
  }
}

std::map<std::string, std::string> Converter::findCycleAttributes(const std::string &line) {
    std::vector<std::string> cycleComponents = Converter::split(line, "<> ");
    std::map<std::string, std::string> result;
    result["__tagName"] = "for";
    result["__variableName"] = cycleComponents[1];
    result["__leftEdge"] = cycleComponents[3];
    result["__rightEdge"] = cycleComponents[5];
    return result;
}

std::map<std::string, std::string> Converter::findAttributes(const std::string &line) {
  std::vector<std::string> withoutPars = Converter::split(line, "</>");
  std::vector<std::string> keyValue = Converter::split(withoutPars[0], " \t");
  std::map<std::string, std::string> result;
  result["__tagName"] = Converter::putBrackets(keyValue[0]);
  for (int i = 1; i < keyValue.size(); i++) {
    std::vector<std::string> element = Converter::split(keyValue[i], "=");
    result[element[0]] =  element[1];
  }
  if (withoutPars.size() > 1) {
    result["__text"] = Converter::putBrackets(withoutPars[1]);
  }
  return result;
}

Converter::Node::Node(std::string _tagName) {
  this->tagName = std::move(_tagName);
}

std::vector<std::string> Converter::parseXML(const std::string &filePath) {
  std::string inputFile = readXMLFile(filePath);
  std::vector<std::string> tags = Converter::specialSplit(normalize(inputFile));
  return tags;
}

std::vector<std::string> Converter::split(const std::string &str, const std::string &delims) {
  std::vector<int> delimIterators;
  delimIterators.push_back(-1);
  int size = (int)str.size();
  for (int i = 0; i < size; i++) {
    for (char delim : delims) {
      if (str[i] == delim) {
        delimIterators.push_back(i);
        break;
      }
    }
  }
  delimIterators.push_back(size);
  int delimCount = (int)delimIterators.size();
  std::vector<std::string> result;
  for (int i = 0; i + 1 < delimCount; i++) {
    int lenSubStr = delimIterators[i + 1] - delimIterators[i] - 1;
    if (lenSubStr > 0) {
      std::string delimSubStr = str.substr(delimIterators[i] + 1, lenSubStr);
      result.push_back(delimSubStr);
    }
  }
  return result;
}

bool Converter::inBound(const std::string &str, int ind) {
  return (ind >= 0 && ind < str.size());
}

std::vector<std::string> Converter::specialSplit(const std::string &str) {
  std::vector<int> delimIterators;
  delimIterators.push_back(-1);
  int size = (int)str.size();
  for (int i = 0; i < size; i++) {
    if (str[i] == '<' || str[i] == '>') {
      delimIterators.push_back(i);
    }
  }
  delimIterators.push_back(size);
  int delimCount = (int)delimIterators.size();
  std::vector<std::string> result;
  for (int i = 0; i + 1 < delimCount; i++) {
    int lenSubStr = delimIterators[i + 1] - delimIterators[i] - 1;
    if (lenSubStr > 0) {
      std::string delimSubStr;
      if (inBound(str, delimIterators[i]) && inBound(str, delimIterators[i + 1]) &&
        str[delimIterators[i]] == '<' && str[delimIterators[i + 1]] == '>')
        if (str[delimIterators[i + 1] - 1] == '/' && i + 2 < delimCount) {
          delimSubStr = std::string(1, '<')
            + str.substr(delimIterators[i] + 1, lenSubStr) + std::string(1, '>')
            + str.substr(delimIterators[i + 1] + 1, delimIterators[i + 2] - delimIterators[i + 1] - 1);
          i++;
        }
        else delimSubStr = std::string(1, '<') + str.substr(delimIterators[i] + 1, lenSubStr) + std::string(1, '>');
      else delimSubStr = str.substr(delimIterators[i] + 1, lenSubStr);
      result.push_back(delimSubStr);
    }
  }
  return result;
}

std::string Converter::joinToString(const std::vector<std::string> &lines) {
  std::string result;
  for (const std::string& line : lines) {
    result += line;
  }
  return result;
}

std::string Converter::trim(const std::string& str, const std::string& whitespace = " \t") {
  const auto strBegin = str.find_first_not_of(whitespace);
  if (strBegin == std::string::npos)
    return ""; // no content

  const auto strEnd = str.find_last_not_of(whitespace);
  const auto strRange = strEnd - strBegin + 1;

  return str.substr(strBegin, strRange);
}

std::string Converter::readXMLFile(const std::string &filePath) {
  std::ifstream file(filePath);
  if (!file.is_open()) {
    throw std::runtime_error("Unable to open file: " + filePath);
  }
  std::stringstream buffer;
  buffer << file.rdbuf();
  return buffer.str();
}

std::string Converter::normalize(const std::string &code) {
  std::vector<std::string>withXMLVersion = Converter::split(code, "\n\r");
  std::vector<std::string>splitStr = Converter::slice(withXMLVersion, 1, (int)withXMLVersion.size() - 1);
  for (std::string &str : splitStr) {
    str = Converter::trim(str, " \t");
  }
  return Converter::joinToString(splitStr);
}

std::string Converter::putBrackets(const std::string &str) {
  return std::string(1, '"') + str + std::string(1, '"');
}

template<typename T>
std::vector<T> Converter::slice(const std::vector<T> &collection, const int left, const int right) {
  std::vector<T> result;
  for (int i = left; i <= right; i++) {
    result.push_back(collection[i]);
  }
  return result;
}

std::string Converter::getTab(int tabSize) {
  std::string result = std::string(tabSize*4, ' ');
  return result;
}

Converter::Converter() = default;

void Converter::rebuildTree(const std::string &filePath) {
  std::vector<Node*> openTags;
  this->buildTree(this->root, 0, Converter::parseXML(filePath), openTags);
}

Converter::Converter(const std::string &filePath) {
  this->rebuildTree(filePath);
}