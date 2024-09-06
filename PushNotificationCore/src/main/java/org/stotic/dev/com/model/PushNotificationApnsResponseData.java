package org.stotic.dev.com.model;

public class PushNotificationApnsResponseData {

    // 失敗の理由を示すエラーコード
    private String reason;
    // APNs がトークンがトピックに対して有効でなくなったことを表すエポックからのミリ秒単位の時間
    private Long timestamp;
}
