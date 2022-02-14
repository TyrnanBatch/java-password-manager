package uk.co.tyrnan;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class hashing {
    static String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] inputEncoded = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(inputEncoded);
    }
}

