//
// Created by User on 02.11.2024.
//

#ifndef XML_PARSER_SUB_QUESTS_CONVERTER_H_
#define XML_PARSER_SUB_QUESTS_CONVERTER_H_

#include <map>
#include <vector>

class Converter {
  class Node {
   public:
    std::string tagName{};
    std::map<std::string, std::vector<Node*>> childNodes{};
    std::map<std::string, std::string> attributes;
    inline explicit Node(std::string _tagName);
  };
  static std::vector<std::string> split(const std::string &str, const std::string &delims);
  static std::string joinToString(const std::vector<std::string> &lines);
  static std::string trim(const std::string &str, const std::string &whitespace);
  static std::string readXMLFile(const std::string &filePath);
  static std::string normalize(const std::string &filePath);
  template<typename T>
  static std::vector<T> slice(const std::vector<T> &collection, int left, int right);
  static std::vector<std::string> specialSplit(const std::string &str);
  static bool inBound(const std::string &str, int ind);
  static std::map<std::string, std::string> findCycleAttributes(const std::string &line);
  static std::map<std::string, std::string> findAttributes(const std::string &line);
  static std::vector<std::string> parseXML(const std::string& filePath);
  static std::string getTab(int tabSize);
  static std::string putBrackets(const std::string &str);
  int getVariableValue(std::string &s);
  std::string getOutput(Node *&v, int tabSize, bool inList);
  std::string ToChild(Node *&v, int tabSize);
  std::string replaceVariables(const std::string &line);
  void buildTree(Node* &v, int ind, const std::vector<std::string> &tags, std::vector<Node*> &openTags);
 public:
  Node* root = nullptr;
  std::map<std::string, int> args;
  inline Converter();
  explicit Converter(const std::string &filePath);
  void rebuildTree(const std::string &filePath);
  void printJSON();
};

#endif //XML_PARSER_SUB_QUESTS_CONVERTER_H_