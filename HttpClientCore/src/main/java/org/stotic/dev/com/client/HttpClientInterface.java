package org.stotic.dev.com.client;

import java.io.IOException;

public interface HttpClientInterface {

    /**
     * GETでHTTPリクエストを行う
     * @param service APIリクエストに必要な情報
     * @return APIのレスポンス
     * @param <ResponseData> レスポンスデータの型
     * @throws IOException
     */
    <ResponseData> ApiResponse<ResponseData> get(ApiServiceInterface service) throws IOException;

    /**
     * POSTでHTTPリクエストを行う
     * @param service APIリクエストに必要な情報
     * @return APIのレスポンス
     * @param <ResponseData> レスポンスデータの型
     * @throws IOException
     */
    <ResponseData> ApiResponse<ResponseData> post(ApiServiceInterface service) throws IOException;
}
