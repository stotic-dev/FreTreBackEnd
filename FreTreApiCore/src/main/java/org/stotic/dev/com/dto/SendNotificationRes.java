package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.stotic.dev.com.api.exception.ApiResultCode;
import org.stotic.dev.com.model.PushNotificationApnsResponseData;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendNotificationRes extends ApiResultDto {

    private final static String SUCCESS_APNS_RESPONSE_CODE = "200";

    private String errorReason;

    public  SendNotificationRes() {}

    public SendNotificationRes(String resultCode, PushNotificationApnsResponseData responseData) {
        super(resultCode == SUCCESS_APNS_RESPONSE_CODE ? ApiResultCode.SUCCESS_SEND_NOTIFICATION.getCode() : ApiResultCode.Failure_SEND_NOTIFICATION.getCode());
        if(responseData != null) {
            errorReason = responseData.getReason();
        }
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
