#!/bin/bash

# 使用方法のヘルプを表示する関数
usage() {
    echo "Usage: $0 [-f <war file path>] [-e <environment (local|stg|prd)>]"
    exit 1
}

# デフォルトのWARファイルパスと環境
WAR_FILE_PATH="../../target/FreTreApiCore.war"
ENVIRONMENT="local"
BUILD_TARGET_POM_PATH="../../../FreTreServer/pom.xml"

# 引数をパース
while getopts "f:e:" opt; do
  case $opt in
    f) WAR_FILE_PATH="$OPTARG" ;;
    e) ENVIRONMENT="$OPTARG" ;;
    *) usage ;;
  esac
done

# 対応するDocker Composeファイルを設定
COMPOSE_FILE=""
case $ENVIRONMENT in
  local) COMPOSE_FILE="docker-compose-local.yml" ;;
  stg) COMPOSE_FILE="docker-compose-stg.yml" ;;
  prd) COMPOSE_FILE="docker-compose-prd.yml" ;;
  *) echo "Invalid environment: $ENVIRONMENT" ; usage ;;
esac

# シェルスクリプトのディレクトリに移動
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

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

# WARファイルを指定の場所にコピー
TARGET_WAR_PATH="../fretre/FreTreApiCore.war"
echo "Copying WAR file from $WAR_FILE_PATH to $TARGET_WAR_PATH..."
cp "$WAR_FILE_PATH" "$TARGET_WAR_PATH"

# docker-compose.ymlファイルのあるディレクトリへ移動
cd ../

# Dockerコンテナを停止
echo "Stopping Docker containers..."
docker-compose -f "$COMPOSE_FILE" down
if [ $? -ne 0 ]; then
    echo "Error: Failed to Deployment on Docker Stopping."
    exit 1
fi

# Dockerコンテナを起動
echo "Starting Docker containers with $COMPOSE_FILE..."
docker-compose -f "$COMPOSE_FILE" up -d
if [ $? -ne 0 ]; then
    echo "Error: Failed to Deployment on Docker Starting."
    exit 1
fi

echo "Deployment completed successfully."