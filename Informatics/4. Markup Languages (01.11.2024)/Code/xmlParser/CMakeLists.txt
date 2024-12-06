cmake_minimum_required(VERSION 3.27)
project(xmlParser)

set(CMAKE_CXX_STANDARD 17)

add_executable(xmlParser main.cpp
        sub_quests/Converter.cpp
        sub_quests/Converter.h)
