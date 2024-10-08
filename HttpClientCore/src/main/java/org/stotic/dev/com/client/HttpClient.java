package org.stotic.dev.com.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.stotic.dev.com.logger.ApiLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClient implements HttpClientInterface {
    private OkHttpClient client = new OkHttpClient();

    public <ResponseData> ApiResponse<ResponseData> get(ApiServiceInterface service, Class<ResponseData> responseType) throws IOException {
        ApiLogger.log.info(String.format("[In] Before GET Request(url=%s, headers=%s, body=%s)", service.getApiUrl(), service.getHttpHeaders().toString(), service.getBody()));
        Request request = new Request.Builder().url(service.getApiUrl())
                .headers(Headers.of(service.getHttpHeaders()))
                .build();
        Response response = client.newCall(request).execute();

        ApiLogger.log.info("Succeed GET Request.");
        Map<String, String> header = convertHeaderToMap(response.headers());

        String responseBodyString = response.body().string();
        ApiLogger.log.info(String.format("Succeed POST Request(status=%s, headers=%s, body=%s).", response.code(), header.toString(), responseBodyString));
        ObjectMapper mapper = new ObjectMapper();
        return new ApiResponse<ResponseData>(response.code(), header, responseBodyString.isBlank() ? null : mapper.readValue(responseBodyString, responseType));
    }

    public <ResponseData> ApiResponse<ResponseData> post(ApiServiceInterface service, Class<ResponseData> responseType) throws IOException {
        ApiLogger.log.info(String.format("[In] Before POST Request(url=%s, headers=%s, body=%s)", service.getApiUrl(), service.getHttpHeaders().toString(), service.getBody()));
        RequestBody body = new ApiRequest(service).getBody();
        Request request = new Request.Builder().url(service.getApiUrl())
                .headers(Headers.of(service.getHttpHeaders()))
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        Map<String, String> header = convertHeaderToMap(response.headers());

        String responseBodyString = response.body().string();
        ApiLogger.log.info(String.format("Succeed POST Request(status=%s, headers=%s, body=%s).", response.code(), header.toString(), responseBodyString));

        ObjectMapper mapper = new ObjectMapper();
        return new ApiResponse<ResponseData>(response.code(), header, responseBodyString.isBlank() ? null : mapper.readValue(responseBodyString, responseType));
    }

    private Map<String, String> convertHeaderToMap(Headers headers) {
        return headers
                .toMultimap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().collect(Collectors.joining())));
    }
}
