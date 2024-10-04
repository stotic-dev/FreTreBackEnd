package org.stotic.dev.com.sequrity.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.utilities.DateUtility;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class JwtPayload {

    // 発行者キー
    private String iss;
    // 発行時刻
    private Long iat;

    public JwtPayload(String iss, Instant iat) {
        this.iss = iss;
        this.iat = DateUtility.dateToUtcSecTimestamp(iat);
    }

    public String getPayload() throws JsonProcessingException {
        String payload = String.format("{ \"iss\": \"%s\", \"iat\": %d }", iss, iat);
                ApiLogger.log.debug(String.format("Payload: %s", payload));
        return JwtDataEncoder.base64UrlEncode(payload.getBytes(StandardCharsets.UTF_8));
    }
}
