cmake_minimum_required(VERSION 3.6)

add_library(native-lib
             SHARED
             native-lib.cpp )

#set(distribution_DIR ../../../../libs)
#
#include_directories(distribution_DIR/include)
#add_library(render
#        SHARED
#        IMPORTED)
#
#set_target_properties(render
#        PROPERTIES IMPORTED_LOCATION
#        ${distribution_DIR}/armeabi-v7a/librender.so)
#
#
#add_library(pixaloop
#        SHARED
#        IMPORTED)
#
#set_target_properties(pixaloop
#        PROPERTIES IMPORTED_LOCATION
#        ${distribution_DIR}/armeabi-v7a/libpixaloop.so)
#
#add_library(tensorflowlite
#        SHARED
#        IMPORTED)
#
#set_target_properties(tensorflowlite
#        PROPERTIES IMPORTED_LOCATION
#        ${distribution_DIR}/armeabi-v7a/libtensorflowlite_jni.so)




find_library(log-lib
        log)

find_library(
        android-lib
        android)

target_link_libraries( native-lib
#        render
#        pixaloop
#        tensorflowlite
        ${log-lib})