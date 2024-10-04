package org.stotic.dev.com.api.exception;

public enum ApiResultCode {

    /**
     * リクエスト内容が想定外の場合
     */
    BAD_REQUEST("000", "Received invalid request."),
    /**
     * 想定外のエラーが発生した場合
     */
    SYSTEM_ERROR("100", "Occurred System Exception");

    private String code;

    private String message;

    ApiResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
