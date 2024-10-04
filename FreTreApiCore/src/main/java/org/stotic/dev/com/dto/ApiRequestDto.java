package org.stotic.dev.com.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stotic.dev.com.logger.ApiLogger;

public class ApiRequestDto implements ContentPrintable {

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
