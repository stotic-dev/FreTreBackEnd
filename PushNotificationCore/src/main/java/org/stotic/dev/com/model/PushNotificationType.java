package org.stotic.dev.com.model;

import java.util.Arrays;

public enum PushNotificationType {

    ALERT("alert");

    PushNotificationType(String key) {
        this.key = key;
    }

    static PushNotificationType getType(String key) {
        return Arrays.stream(PushNotificationType.class
                        .getEnumConstants())
                        .filter(type -> { return type.key == key; })
                        .findFirst().orElseThrow();
    }

    private String key;

    public String getKey() {
        return key;
    }
}
