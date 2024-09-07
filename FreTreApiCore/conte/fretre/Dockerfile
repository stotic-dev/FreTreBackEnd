FROM payara/server-full:6.2022.1-jdk17

# デプロイコマンドのパラメータ指定
ENV DEPLOY_PROPS='--name FreTreServer'

# WARファイルをコンテナ内のdeploymentsディレクトリにコピー
COPY ./fretre/FreTreApiCore.war $DEPLOY_DIR
