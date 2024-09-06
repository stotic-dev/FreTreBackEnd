package org.stotic.dev.com.client;

import java.net.URL;
import java.util.Map;

public class ApiResponse<T> {

    private Integer status;
    private T data;

    public ApiResponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
