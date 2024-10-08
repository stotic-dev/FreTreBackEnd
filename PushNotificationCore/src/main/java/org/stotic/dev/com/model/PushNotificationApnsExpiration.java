package org.stotic.dev.com.model;

import java.time.Instant;
import java.time.Period;

public record PushNotificationApnsExpiration(String expirationEpoc) {

    private static final int DEFAULT_EXPIRATION_DAY = 2;

    public PushNotificationApnsExpiration(String expirationEpoc) {
        if (expirationEpoc == null || expirationEpoc.isEmpty()) {
            this.expirationEpoc = getDefaultExpirationEpoc();
        } else {
            this.expirationEpoc = expirationEpoc;
        }
    }

    private String getDefaultExpirationEpoc() {
        long secEpoc = Instant.now().plus(Period.ofDays(DEFAULT_EXPIRATION_DAY)).getEpochSecond();
        return Long.toString(secEpoc);
    }
}
