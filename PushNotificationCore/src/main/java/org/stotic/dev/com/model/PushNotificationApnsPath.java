package org.stotic.dev.com.model;

import jakarta.validation.constraints.NotNull;

public class PushNotificationApnsPath {

    private String baseUrl;
    private String destinationToken;

    // APNSにリクエストするURLのベースパス
    private static final String APNS_BASE_PATH = "/3/device";

    public PushNotificationApnsPath(@NotNull String baseUrl, @NotNull String destinationToken) {
        this.baseUrl = baseUrl;
        this.destinationToken = destinationToken;
    }

    public String getRequestUrl() {
        return String.format("%s%s/%s", baseUrl, APNS_BASE_PATH, destinationToken);
    }
}
