package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.stotic.dev.com.api.exception.ApiResultCode;
import org.stotic.dev.com.client.ApiResponse;
import org.stotic.dev.com.model.PushNotificationApnsResponseData;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendNotificationRes extends ApiResultDto {

    private static final int SUCCESS_RESULT_CODE = 200;

    private String errorReason;

    public  SendNotificationRes() {}

    public SendNotificationRes(List<ApiResponse<PushNotificationApnsResponseData>> responseList) {
        super(
                responseList.stream()
                        .map(ApiResponse::getStatus)
                        .filter(Predicate.not(Predicate.isEqual(SUCCESS_RESULT_CODE)))
                        .findFirst().isEmpty()
                        ? ApiResultCode.SUCCESS_SEND_NOTIFICATION.getCode()
                        : ApiResultCode.FAILURE_SEND_NOTIFICATION.getCode()
        );
        List<String> errorReasons = responseList.stream()
                .filter(reasons -> reasons.getData() != null && !reasons.getData().getReason().isEmpty())
                .map(data -> data.getData().getReason())
                .toList();
        if(!errorReasons.isEmpty()) {
            errorReason = errorReasons.stream().collect(Collectors.joining(", "));
        }
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
