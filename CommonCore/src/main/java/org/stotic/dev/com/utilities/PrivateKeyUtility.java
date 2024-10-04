package org.stotic.dev.com.utilities;

import org.stotic.dev.com.logger.ApiLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyUtility {

    private static final String BEGIN_PART_STRING = "-----BEGIN PRIVATE KEY-----";
    private static final String END_PART_STRING = "-----END PRIVATE KEY-----";

    public static PrivateKey getPrivateKey(String filename, String algorithm) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        ApiLogger.log.debug(String.format("Read private key(fileName=%s, content=%s)", filename, content));
        try {
            String privateKey = content.replace(BEGIN_PART_STRING, "")
                    .replace(END_PART_STRING, "")
                    .replaceAll("\\s+", "");
            ApiLogger.log.debug(String.format("Fixed private key(fileName=%s, content=%s)", filename, privateKey));
            KeyFactory kf = KeyFactory.getInstance(algorithm);

            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String.format("Java did not support the algorithm: %s(error=%s)", algorithm, e.toString()));
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(String.format("Invalid key format(error=%s)", e.toString()));
        }
    }
}
