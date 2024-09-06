package org.stotic.dev.com.sequrity.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("alg", algorithm.getValue());
        headerMap.put("kid", keyId);
        byte[] jsonHeader = mapper.writeValueAsBytes(headerMap);
        return Base64.getEncoder().encodeToString(jsonHeader);
    }
}