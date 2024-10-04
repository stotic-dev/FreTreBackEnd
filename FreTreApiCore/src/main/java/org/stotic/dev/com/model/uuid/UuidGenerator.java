package org.stotic.dev.com.model.uuid;

import jakarta.enterprise.context.ApplicationScoped;
import org.stotic.dev.com.logger.ApiLogger;

import java.util.UUID;

@ApplicationScoped
public class UuidGenerator {

    public UuidGenerator() {
        ApiLogger.log.info("[In]");
    }

    public UUID getUuid() {
        return UUID.randomUUID();
    }
}
