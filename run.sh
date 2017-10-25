#!/bin/bash
kotlinc com/cubett/ -include-runtime -d Cubett.jar
if [ $? -ne 0 ]; then
    echo "Build failed"
    exit 1
fi

if [ $# -eq 0 ] || [ $1 != "--release" ]; then
    java -jar Cubett.jar
else
    java -jar Cubett.jar --release
fi
