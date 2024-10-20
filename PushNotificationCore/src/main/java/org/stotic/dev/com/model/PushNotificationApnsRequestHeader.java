package org.stotic.dev.com.model;

import jakarta.validation.constraints.NotNull;
import org.stotic.dev.com.logger.ApiLogger;

import java.util.HashMap;
import java.util.Map;

public class PushNotificationApnsRequestHeader {

    public PushNotificationApnsRequestHeader(@NotNull String authorization, @NotNull PushNotificationType pushType, String apnsId, PushNotificationApnsExpiration apnsExpiration, PushNotificationPriority apnsPriority, String apnsTopic, String apnsCollapseId) {
        ApiLogger.log.debug(String.format("[In] authorization=%s, pushType=%s, apnsId=%s, apnsExpiration=%s, apnsPriority=%s, apnsTopic=%s, apnsCollapseId=%s", authorization, pushType.getKey(), apnsId, apnsExpiration, apnsPriority.getValue(), apnsTopic, apnsCollapseId));
        this.authorization = authorization;
        this.pushType = pushType;
        this.apnsId = apnsId;
        this.apnsExpiration = apnsExpiration.expirationEpoc();
        this.apnsPriority = apnsPriority;
        this.apnsTopic = apnsTopic;
        this.apnsCollapseId = apnsCollapseId;
    }

    // 通知を送信することを許可する暗号化されたトークン
    @NotNull
    private final String authorization;
    // 通知するペイロードの種別
    @NotNull
    private final PushNotificationType pushType;
    // 通知の一意のID(オプション)
    private final String apnsId;
    // 通知が無効になる日付(オプション)
    private final String apnsExpiration;
    // 通知の優先順位(オプション)
    private final PushNotificationPriority apnsPriority;
    // 通知のトピック(バンドルID/アプリID)(オプション)
    private final String apnsTopic;
    // 複数の通知をユーザーへの1つの通知にマージするために使用する識別子(オプション)
    private final String apnsCollapseId;

    public Map<String, String> getHeaders() {
        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", authorization);
        headersMap.put("apns-push-type", pushType.getKey());
        headersMap.put("apns-expiration", apnsExpiration);

        if (apnsId != null) {
            headersMap.put("apns-id", apnsId);
        }

        if (apnsPriority != null) {
            headersMap.put("apns-priority", apnsPriority.getValue());
        }

        if (apnsTopic != null) {
            headersMap.put("apns-topic", apnsTopic);
        }

        if (apnsCollapseId != null) {
            headersMap.put("apns-collapse-id", apnsCollapseId);
        }

        ApiLogger.log.debug(String.format("apns-header=%s", headersMap.toString()));

        return headersMap;
    }
}
