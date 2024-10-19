package org.stotic.dev.com.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.sequrity.jwt.JwtGenerator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;

public class PushNotificationApnsAuthorization {

    private final String authorizationToken;

    public PushNotificationApnsAuthorization(PrivateKey privateKey, String teamId, String keyId) throws SystemException {
        try {
            String jwtToken = JwtGenerator.generateJwtToken(privateKey, keyId, teamId);
            ApiLogger.log.debug("Succeed generate jwt token.");
            authorizationToken = String.format("Bearer %s", jwtToken);
        } catch (NoSuchAlgorithmException | SignatureException | JsonProcessingException | InvalidKeyException e) {
            throw new SystemException(e.toString());
        }

        ApiLogger.log.info(String.format("Complete initialize PushNotificationApnsAuthorization(privateKey=%s, teamId=%s, keyId=%s)", privateKey.toString(), teamId, keyId));
    }

    public String getAuthorizationToken() {
        return this.authorizationToken;
    }
}
