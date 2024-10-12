#!/bin/bash

# 使用方法のヘルプを表示する関数
usage() {
    echo "Usage: $0 [-f <war file path>] [-p <payara script file path>] [-t <app team id>] [-k <apns notification key id>] [-c <common property file path>]"
    exit 1
}

# デフォルトのWARファイルパスと環境
WAR_FILE_PATH="../../target/FreTreApiCore.war"
BUILD_SCRIPT_FILE="build_app.sh"
PAYARA_SERVER_SCRIPT="/opt/payara6/bin/asadmin"
APP_TEAM_ID=null
APNS_NOTIFICATION_KEY_ID=null
DOMAIN="domain1"
FRETRE_COMMON_PROPERTY_PATH="/opt/app/resources/common.property"

# 引数をパース
while getopts "f:p:t:k:c:" opt; do
  case $opt in
    f) WAR_FILE_PATH="$OPTARG" ;;
    p) PAYARA_SERVER_SCRIPT="$OPTARG" ;;
    t) APP_TEAM_ID="$OPTARG" ;;
    k) APNS_NOTIFICATION_KEY_ID="$OPTARG" ;;
    c) FRETRE_COMMON_PROPERTY_PATH="$OPTARG" ;;
    *) usage ;;
  esac
done

# シェルスクリプトのディレクトリに移動
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

# Payaraサーバーのドメイン起動チェックと一時停止
echo "Checking if Payara domain '$DOMAIN' is running..."
$PAYARA_SERVER_SCRIPT list-domains | grep "$DOMAIN running" > /dev/null
if [ $? -ne 0 ]; then
    echo "Payara domain '$DOMAIN' is not running."
else
    echo "Payara domain '$DOMAIN' is already running."
    $PAYARA_SERVER_SCRIPT stop-domain $DOMAIN || { echo "Failed to stop Payara $DOMAIN"; exit 1; }
fi

# プロジェクトのビルド開始
bash $BUILD_SCRIPT_FILE

# Payaraサーバーのドメイン起動
echo "Payara domain '$DOMAIN' is running..."
$PAYARA_SERVER_SCRIPT start-domain $DOMAIN || { echo "Failed to start Payara domain"; exit 1; }

# システムプロパティが指定されている場合は設定
if [ "$APP_TEAM_ID" != "null" ]; then
    echo "Setting system property: teamId=$APP_TEAM_ID"
    $PAYARA_SERVER_SCRIPT create-system-properties teamId=$APP_TEAM_ID
fi

if [ "$APNS_NOTIFICATION_KEY_ID" != "null" ]; then
    echo "Setting system property: apnsKeyId=$APNS_NOTIFICATION_KEY_ID"
    $PAYARA_SERVER_SCRIPT create-system-properties apnsKeyId=$APNS_NOTIFICATION_KEY_ID
fi

if [ "$FRETRE_COMMON_PROPERTY_PATH" != "null" ]; then
    echo "Setting system property: commonPropertyPath=$FRETRE_COMMON_PROPERTY_PATH"
    $PAYARA_SERVER_SCRIPT create-system-properties commonPropertyPath=$FRETRE_COMMON_PROPERTY_PATH
fi

# システムプロパティの確認
echo "Checking application system property..."
$PAYARA_SERVER_SCRIPT list-system-properties

# WARファイルのデプロイ
echo "Deploying the WAR file: $WAR_FILE_PATH"
$PAYARA_SERVER_SCRIPT deploy --force true --contextroot fretre-api --name FreTreApiCore $WAR_FILE_PATH || { echo "Failed to deploy the WAR file"; exit 1; }

echo "Deployment completed successfully!"