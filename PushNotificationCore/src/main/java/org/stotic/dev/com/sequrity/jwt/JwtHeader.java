package org.stotic.dev.com.sequrity.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stotic.dev.com.logger.ApiLogger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class JwtHeader {

    private Algorithm algorithm;
    private String keyId;

    public JwtHeader(Algorithm algorithm, String keyId) {
        this.algorithm = algorithm;
        this.keyId = keyId;
    }

    public String getHeader() throws JsonProcessingException {
        String header = String.format("{ \"alg\": \"%s\", \"kid\": \"%s\" }", algorithm.getValue(), keyId);
        ApiLogger.log.debug(String.format("Header: %s", header));
        return JwtDataEncoder.base64UrlEncode(header.getBytes(StandardCharsets.UTF_8));
    }
}