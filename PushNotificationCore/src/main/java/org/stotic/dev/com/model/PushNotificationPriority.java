package org.stotic.dev.com.model;

import java.util.Arrays;

public enum PushNotificationPriority {

    HIGH("10"),
    MIDDLE("5"),
    LOW("1");

    PushNotificationPriority(String value) {
        this.value = value;
    }

    static PushNotificationPriority getPriority(String priority) {
        return Arrays.stream(PushNotificationPriority.class.getEnumConstants())
                .filter( type -> { return type.value == priority; })
                .findFirst()
                .orElseThrow();
    }

    private String value;

    public String getValue() {
        return value;
    }
}
