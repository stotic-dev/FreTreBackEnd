package org.stotic.dev.com.client;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Map;

public class ApiRequest {

    private Map<String, String> headers;
    private String body;

    public ApiRequest(ApiServiceInterface service) {
        this.headers = service.getHttpHeaders();
        this.body = service.getBody();
    }

    public Headers getHeader() {
        return Headers.of(headers);
    }

    public RequestBody getBody() {
        return RequestBody.create(body, MediaType.parse("application/json; charset=utf-8"));
    }
}
