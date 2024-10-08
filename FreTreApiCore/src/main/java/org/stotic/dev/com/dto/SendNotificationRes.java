package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.stotic.dev.com.model.PushNotificationApnsResponseData;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendNotificationRes extends ApiResultDto {

    private String errorReason;

    public SendNotificationRes(String resultCode) {
        super(resultCode);
    }

    public  SendNotificationRes() {}

    public SendNotificationRes(String resultCode, PushNotificationApnsResponseData responseData) {
        super(resultCode);
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
