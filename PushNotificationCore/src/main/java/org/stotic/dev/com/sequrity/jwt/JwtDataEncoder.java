package org.stotic.dev.com.sequrity.jwt;

import java.util.Base64;

public class JwtDataEncoder {

    public static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
}
