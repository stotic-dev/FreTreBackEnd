package org.stotic.dev.com.model.privateKey;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.model.property.CommonPropertyKey;
import org.stotic.dev.com.model.property.CommonPropertyReader;
import org.stotic.dev.com.utilities.PrivateKeyUtility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

public class ApnsPushNotificationPrivateKeyAccessor {

    private PrivateKey privateKey;

    private static final String APNS_PRIVATE_KEY_ALGORITHMS = "EC";

    @Inject
    public ApnsPushNotificationPrivateKeyAccessor(String filePath) {
        ApiLogger.log.info("[In]");

        try {
            privateKey = PrivateKeyUtility.getPrivateKey(filePath, APNS_PRIVATE_KEY_ALGORITHMS);
            ApiLogger.log.info("Complete initialize ApnsPushNotificationPrivateKeyAccessor");
        } catch (IOException e) {
            ApiLogger.log.error(String.format("Failed load apns private key(privateKeyPath=%s, error=%s)", filePath, e.toString()));
        }
    }

    public PrivateKey getPrivateKey() throws SystemException {
        if (privateKey == null) {
            throw new SystemException("Failed read apns private key");
        }
        return privateKey;
    }
}
