package org.stotic.dev.com.dto;

public abstract class ApiResultDto {
    String resultCode;

    public ApiResultDto(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
