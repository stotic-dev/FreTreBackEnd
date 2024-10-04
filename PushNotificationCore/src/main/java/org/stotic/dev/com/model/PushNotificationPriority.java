package org.stotic.dev.com.model;

import org.stotic.dev.com.logger.ApiLogger;

import java.util.Arrays;

public enum PushNotificationPriority {

    HIGH("10"),
    MIDDLE("5"),
    LOW("1");

    PushNotificationPriority(String value) {
        this.value = value;
    }

    public static PushNotificationPriority getPriority(String priority) {
        ApiLogger.log.info(String.format("[In] priority: %s", priority));
        return Arrays.stream(PushNotificationPriority.class.getEnumConstants())
                .filter( item -> item.value.equals(priority))
                .findFirst()
                .orElseThrow();
    }

    private String value;

    public String getValue() {
        return value;
    }
}
