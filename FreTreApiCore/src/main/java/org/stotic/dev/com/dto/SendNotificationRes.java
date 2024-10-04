package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SendNotificationRes extends ApiResultDto {

    private String errorReason;

    public SendNotificationRes(String resultCode) {
        super(resultCode);
    }

    public  SendNotificationRes() {}

    public SendNotificationRes(String resultCode, String errorReason) {
        super(resultCode);
        this.errorReason = errorReason;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
