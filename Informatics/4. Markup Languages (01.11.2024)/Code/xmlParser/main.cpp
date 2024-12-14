#include <iostream>
#include "sub_quests/Converter.h"
#include <string>
#include <ctime>

int main() {
  std::string path = "../schedule.xml";
  Converter conv(path);
  conv.printJSON();
  std::cout << "\nВремя выполнения по 1 запуску: " << clock()/1000 << " мс.";
  return 0;
}
