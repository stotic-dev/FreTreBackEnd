#!/bin/bash

# 使用方法のヘルプを表示する関数
usage() {
    echo "Usage: $0 [-e <environment (local|stg|prd)>]"
    exit 1
}

# 引数をパース
while getopts "f:e:" opt; do
  case $opt in
    e) ENVIRONMENT="$OPTARG" ;;
    *) usage ;;
  esac
done

# デフォルトの環境
ENVIRONMENT="local"

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

# docker-compose.ymlファイルのあるディレクトリへ移動
cd ../

# Docker Composeを停止
echo "Stopping current Docker containers..."
docker-compose -f "$COMPOSE_FILE" down
if [ $? -ne 0 ]; then
    echo "Error: Failed to stop Docker containers."
    exit 1
fi

echo "Stop server completed successfully."