#!/bin/bash

BUILD_TARGET_POM_PATH="../../../FreTreServer/pom.xml"

# FreTreServerのpomからpackage生成
echo "Cleaning maven..."
mvn clean -f $BUILD_TARGET_POM_PATH
if [ $? -ne 0 ]; then
    echo "Error: Failed to clean with FreTreServer."
    exit 1
fi
echo "Finish clean maven."

echo "Pachaging maven..."
mvn package -f $BUILD_TARGET_POM_PATH -DskipTests
if [ $? -ne 0 ]; then
    echo "Error: Failed to package with FreTreServer."
    exit 1
fi
echo "Finish package maven."