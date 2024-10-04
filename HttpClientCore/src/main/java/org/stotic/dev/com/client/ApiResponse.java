package org.stotic.dev.com.client;

import java.net.URL;
import java.util.Map;

public class ApiResponse<T> {

    private Integer status;
    private Map<String, String> header;
    private T data;

    public ApiResponse(Integer status, Map<String, String> header, T data) {
        this.status = status;
        this.header = header;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public T getData() {
        return data;
    }

}
