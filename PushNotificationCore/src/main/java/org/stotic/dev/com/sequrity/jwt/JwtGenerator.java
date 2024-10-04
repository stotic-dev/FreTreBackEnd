package org.stotic.dev.com.sequrity.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.stotic.dev.com.logger.ApiLogger;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.Instant;
import java.util.Base64;

public class JwtGenerator {

    public static String generateJwtToken(PrivateKey privateKey, String keyId, String teamId) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, JsonProcessingException {
        // HeaderのBase64 URL-Safe文字列を生成
        final JwtHeader header = new JwtHeader(Algorithm.ES256, keyId);
        final String headerEncodeString = header.getHeader();
        // PayloadのBase64 URL-Safe文字列を生成
        final JwtPayload payload = new JwtPayload(teamId, Instant.now());
        final String payloadEncodeString = payload.getPayload();

        String jwtOriginData = headerEncodeString + "." + payloadEncodeString;

        // headerとpayloadを秘密鍵で署名

        final Signature signature = Signature.getInstance("SHA256withECDSAinP1363Format");
//        final Signature signature = Signature.getInstance("SHA256withECDSA");

        signature.initSign(privateKey);
        signature.update(jwtOriginData.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = signature.sign();

        // header + payload + 著名を結合させてjwt tokenを生成
        final String signatureStr = JwtDataEncoder.base64UrlEncode(signatureBytes);
        ApiLogger.log.debug(String.format("Jwt signature: %s", signatureStr));

        return jwtOriginData + "." + signatureStr;
    }
}
