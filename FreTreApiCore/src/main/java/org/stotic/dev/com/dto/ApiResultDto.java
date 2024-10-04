package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stotic.dev.com.logger.ApiLogger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiResultDto implements ContentPrintable {
    String resultCode;

    public ApiResultDto(String resultCode) {
        this.resultCode = resultCode;
    }

    public  ApiResultDto() { resultCode = null; }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toStringContent() {
        try {
            String jsonString = new ObjectMapper().writeValueAsString(this);
            return jsonString;
        }
        catch (JsonProcessingException e) {
            ApiLogger.log.error("Failed parse object(" + e.toString() + ")");
            return "-";
        }
    }
}
