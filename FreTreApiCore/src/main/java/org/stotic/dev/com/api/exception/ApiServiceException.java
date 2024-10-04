package org.stotic.dev.com.api.exception;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;

public class ApiServiceException extends Exception {

    private ApiResultCode resultCode;

    public ApiServiceException(ApiResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ApiResultCode getResultCode() {
        return resultCode;
    }
}
