package org.stotic.dev.com.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PushNotificationApnsPath {

    private String baseUrl;
    private List<String> destinationTokens;

    // APNSにリクエストするURLのベースパス
    private static final String APNS_BASE_PATH = "/3/device";

    public PushNotificationApnsPath(@NotNull String baseUrl, @NotNull List<String> destinationTokens) {
        this.baseUrl = baseUrl;
        this.destinationTokens = destinationTokens;
    }

    public String getRequestUrl() {
        return String.format("%s%s/%s", baseUrl, APNS_BASE_PATH, destinationTokens.stream().collect(Collectors.joining()));
    }
}
