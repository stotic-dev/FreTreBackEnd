package org.stotic.dev.com.sequrity.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;

import java.time.Instant;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtGeneratorTest {

    private ECPublicKey EC_PUBLIC_KEY;
    private ECPrivateKey EC_PRIVATE_KEY;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        // 署名鍵生成
        // ※Sign in with Appleの場合は、コンソールから生成した秘密鍵を利用する。https://developer.apple.com/documentation/sign_in_with_apple/generate_and_validate_tokens
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();
        EC_PUBLIC_KEY = (ECPublicKey) keyPair.getPublic();
        EC_PRIVATE_KEY = (ECPrivateKey) keyPair.getPrivate();
        byte[] publicKeyEncodedBytes = Base64.getEncoder().encode(EC_PUBLIC_KEY.getEncoded());
        byte[] privateKeyEncodedBytes = Base64.getEncoder().encode(EC_PRIVATE_KEY.getEncoded());
        System.out.println("ES256 Public Key:" + new String(publicKeyEncodedBytes));
        System.out.println("ES256 Private Key:" + new String(privateKeyEncodedBytes));
    }

    @Test
    void generateJwtToken() {
        try {
            String jwtToken = JwtGenerator.generateJwtToken(EC_PRIVATE_KEY, "keyId", "teamId");
            final Boolean verifyResult = verifyJWT(jwtToken, EC_PUBLIC_KEY);

            assertTrue(verifyResult);
        }
        catch(Exception e) {
            e.printStackTrace();
            Assertions.fail(String.format("予期せぬ例外発生(error=%s)", e.toString()));
        }
    }

    @Test
    void apnsHeaderValidate() throws JsonProcessingException {
        JwtHeader header = new JwtHeader(Algorithm.ES256, "PC6F4BA63U");
        Assertions.assertEquals("eyAiYWxnIjogIkVTMjU2IiwgImtpZCI6ICJQQzZGNEJBNjNVIiB9", header.getHeader());
    }

    @Test
    void apnsJwtPayloadValidate() throws JsonProcessingException {
        Instant ist = Instant.ofEpochSecond(1728031127);
        JwtPayload payload = new JwtPayload("56LYVN6DMF", ist);
        Assertions.assertEquals("eyAiaXNzIjogIjU2TFlWTjZETUYiLCAiaWF0IjogMTcyODAzMTEyNyB9", payload.getPayload());
    }

    // 署名検証
    public Boolean verifyJWT(String jwt, PublicKey publicKey) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException {
        final String[] splitJwt = jwt.split("\\.");
        final String jwtHeaderStr = splitJwt[0];
        final String jwtPayloadStr = splitJwt[1];
        final String jwtSignatureStr = splitJwt[2];
        System.out.println("header: " + jwtHeaderStr);
        System.out.println("payload: " + jwtPayloadStr);
        System.out.println("signature: " + jwtSignatureStr);
        final Signature jwtSignature = Signature.getInstance("SHA256withECDSAinP1363Format");
        System.out.println(String.format("Start init verify"));
        jwtSignature.initVerify(publicKey);
        System.out.println(String.format("Start verify"));
        jwtSignature.update((jwtHeaderStr + "." + jwtPayloadStr).getBytes());

        if (jwtSignature.verify(Base64.getUrlDecoder().decode(jwtSignatureStr))) {
            System.out.println("Verifying Signature Success");
            return true;
        } else {
            System.out.println("Verifying Signature Failure");
            return false;
        }
    }
}