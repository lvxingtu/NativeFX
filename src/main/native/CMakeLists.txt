# at least cmake 3.9 is required to build this project
cmake_minimum_required(VERSION 3.9)

project(nativefx)

# this project relies on C++11
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# library sources
set(SOURCES		
    src/main/cpp/nativefx.cpp
    src/main/cpp/jnitypeconverter.cpp
)

set(CMAKE_BUILD_TYPE RELEASE)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
include_directories("src/main/headers/")
include_directories("ext/headers/")

# create a shared library from the sources and link it against libs.
add_library(nativefx SHARED ${SOURCES})

# todo: is there a better way to detect linux?
if(UNIX AND NOT APPLE)
    set(LINUX TRUE)
endif()

if(LINUX)
    target_link_libraries(nativefx rt pthread)
endif()

message("> JNI Libs: ${JNI_LIBRARIES}")
#target_link_libraries(nativefx ${JNI_LIBRARIES})
#target_link_libraries (nativefx libname)
