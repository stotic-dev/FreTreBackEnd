package org.stotic.dev.com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotificationApnsResponseData {

    // 失敗の理由を示すエラーコード
    private String reason;
    // APNs がトークンがトピックに対して有効でなくなったことを表すエポックからのミリ秒単位の時間
//    private Long timestamp;

    public PushNotificationApnsResponseData() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

//    public Long getTimestamp() {
//        return timestamp;
//    }

//    public void setTimestamp(Long timestamp) {
//        this.timestamp = timestamp;
//    }
}
