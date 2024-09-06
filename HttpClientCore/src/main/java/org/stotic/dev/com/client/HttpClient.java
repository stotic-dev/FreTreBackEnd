package org.stotic.dev.com.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient implements HttpClientInterface {
    private OkHttpClient client = new OkHttpClient();

    public <ResponseData> ApiResponse<ResponseData> get(ApiServiceInterface service) throws IOException {
        Request request = new Request.Builder().url(service.getApiUrl()).build();
        Response response = client.newCall(request).execute();
        return new ApiResponse<ResponseData>(response.code(), new ObjectMapper().readValue(response.body().string(), new TypeReference<>() {}));
    }

    public <ResponseData> ApiResponse<ResponseData> post(ApiServiceInterface service) throws IOException {
        RequestBody body = new ApiRequest(service).getBody();
        Request request = new Request.Builder().url(service.getApiUrl()).post(body).build();
        Response response = client.newCall(request).execute();
        return new ApiResponse<ResponseData>(response.code(), new ObjectMapper().readValue(response.body().string(), new TypeReference<>() {}));
    }
}
