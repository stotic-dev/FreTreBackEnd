package org.stotic.dev.com.model.restClient.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.stotic.dev.com.client.ApiServiceInterface;
import org.stotic.dev.com.model.PushNotificationApnsRequestHeader;

import java.util.Map;

public final class ApnsPushNotificationHttpService implements ApiServiceInterface {

    private final String url;
    private final PushNotificationApnsRequestHeader header;
    private final String body;

    public ApnsPushNotificationHttpService(@NotBlank String url, @NotNull PushNotificationApnsRequestHeader header, String body) {
        this.url = url;
        this.header = header;
        this.body = body;
    }

    @Override
    public String getApiUrl() {
        return url;
    }

    @Override
    public Map<String, String> getHttpHeaders() {
        return header.getHeaders();
    }

    @Override
    public String getBody() {
        return body;
    }
}
