#include <iostream>
#include "sub_quests/Converter.h"
#include <string>
#include <ctime>

int main() {
  std::string path = "../schedule.xml";
  for (int i = 0; i < 1; i++) {
    Converter conv(path);
    conv.printJSON();
  }
  std::cout << "Время выполнения по 1000 запускам: " << clock()/1000 << " мс.";
  return 0;
}
