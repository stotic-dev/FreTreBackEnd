package org.stotic.dev.com.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.stotic.dev.com.client.ApiResponse;
import org.stotic.dev.com.dto.SendNotificationRes;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.dto.SendNotificationReq;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.model.*;
import org.stotic.dev.com.model.jwt.ApnsTokenRepository;
import org.stotic.dev.com.model.property.CommonPropertyKey;
import org.stotic.dev.com.model.property.CommonPropertyReader;
import org.stotic.dev.com.model.restClient.ApnsPushNotificationClient;
import org.stotic.dev.com.model.restClient.service.ApnsPushNotificationHttpService;
import org.stotic.dev.com.model.uuid.UuidGenerator;

import java.util.UUID;

@ApplicationScoped
public class SendNotificationLogic implements ApiLogic<SendNotificationReq, SendNotificationRes> {

    @Inject
    private CommonPropertyReader propertyReader;
    @Inject
    private ApnsTokenRepository apnsTokenRepository;
    @Inject
    private UuidGenerator uuid;
    @Inject
    private ApnsPushNotificationClient apnsPushNotificationClient;
    
    public SendNotificationLogic() {}

    @Override
    public SendNotificationRes process(SendNotificationReq input) throws SystemException {

        ApiLogger.log.info("[In]");
        // リクエストの取得とチェック

        // Apnsへのリクエストの作成
        // ApnsへリクエストするためのURLを作成
        String baseUrl = propertyReader.getValue(input.getDevelopFlg() ? CommonPropertyKey.APNS_DEV_CONNECT_URL : CommonPropertyKey.APNS_PRD_CONNECT_URL);
        PushNotificationApnsPath path = new PushNotificationApnsPath(baseUrl, input.getDestinations());

        // Apnsへのリクエストヘッダー作成
        PushNotificationApnsRequestHeader header = createApnsRequestHeader(input);

        // Apnsへリクエスト
        ApnsPushNotificationHttpService service = new ApnsPushNotificationHttpService(path.getRequestUrl(), header, input.getPayload());
        ApiResponse<PushNotificationApnsResponseData> response = apnsPushNotificationClient.execute(service);

        ApiLogger.log.info("[End]");
        return new SendNotificationRes(response.getStatus(), response.getData());
    }

    private PushNotificationApnsRequestHeader createApnsRequestHeader(SendNotificationReq req) throws SystemException {
        // Apnsへリクエストするための認証トークンを作成
        PushNotificationApnsAuthorization authorization = apnsTokenRepository.build();

        // Apnsへリクエストするための通知タイプを指定
        PushNotificationType pushType = PushNotificationType.ALERT;

        // Apnsへリクエストする通知を識別するIDを生成
        UUID appId = uuid.getUuid();

        // Apnsへリクエストする通知の保存期間を指定
        PushNotificationApnsExpiration expiration = new PushNotificationApnsExpiration(req.getNotificationExpiration());

        // Apnsへリクエストする通知の優先度を指定
        PushNotificationPriority priority = PushNotificationPriority.getPriority(req.getNotificationPriority().toString());

        // Apnsへリクエストする通知のtopicIDを指定
        String apnsTopic = propertyReader.getValue(CommonPropertyKey.APP_BUNDLE_ID);

        return new PushNotificationApnsRequestHeader(authorization.getAuthorizationToken(), pushType, appId.toString(), expiration, priority, apnsTopic, req.getCollapseId());
    }
}
