package org.stotic.dev.com.model.jwt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.model.PushNotificationApnsAuthorization;
import org.stotic.dev.com.model.privateKey.ApnsPushNotificationPrivateKeyAccessor;
import org.stotic.dev.com.model.property.CommonPropertyKey;
import org.stotic.dev.com.model.property.CommonPropertyReader;
import org.stotic.dev.com.model.property.SystemPropertyKey;
import org.stotic.dev.com.utilities.SystemVariableUtility;

import java.security.PrivateKey;
import java.time.Instant;

@ApplicationScoped
public class ApnsTokenRepository {

    // 現在保持しているJWT
    private PushNotificationApnsAuthorization apnsAuthorization;
    // JWT更新時間
    private Instant updateTime;

    // JWT保持期間(秒)
    private static final Long TOKEN_RETENTION_PERIOD = (long) (60 * 50);

    @Inject
    private CommonPropertyReader propertyReader;

    public ApnsTokenRepository() {
        updateTime = Instant.ofEpochSecond(0);
    }

    /**
     * Apnsの認証トークンを返す
     * 認証トークンは生成してから50分保持し50分間は保持しているトークンを返す
     * 50分経過している場合は再度新しいトークンを発行して返す
     *
     * @return PushNotificationApnsAuthorization: 認証トークン
     * @throws SystemException
     */
    public PushNotificationApnsAuthorization build() throws SystemException {
        Instant now = Instant.now();
        if((now.getEpochSecond() - updateTime.getEpochSecond()) >=TOKEN_RETENTION_PERIOD) {
            ApiLogger.log.info(String.format("Necessary update authorization token(prevUpdate=%s, now=%s)", updateTime, now));
            this.apnsAuthorization = generateAuthorization();
            this.updateTime = now;
        }

        return this.apnsAuthorization;
    }

    private PushNotificationApnsAuthorization generateAuthorization() throws SystemException {
        // Apnsへリクエストするための認証トークンを作成
        ApnsPushNotificationPrivateKeyAccessor accessor = new ApnsPushNotificationPrivateKeyAccessor(propertyReader.getValue(CommonPropertyKey.APNS_PRIVATE_KEY_PATH));
        PrivateKey privateKey = accessor.getPrivateKey();
        String teamId = SystemVariableUtility.getValue(SystemPropertyKey.APP_TEAM_ID);
        String keyId = SystemVariableUtility.getValue(SystemPropertyKey.APNS_KEY_ID);
        return new PushNotificationApnsAuthorization(privateKey, teamId, keyId);
    }
}
