package com.ecomerce.api.util;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class OtpUtil {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String ALGORITHM = "AES";

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256);
        return keyGen.generateKey();
    }

    public static String encryptOTP(String otp, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedOtpBytes = cipher.doFinal(otp.getBytes());
        return Base64.getEncoder().encodeToString(encryptedOtpBytes);
    }

    public static String keyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static SecretKey stringToKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    public static String generateStrongPassword() {
        CharacterRule lowercaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule uppercaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);

        PasswordGenerator generator = new PasswordGenerator();

        return generator.generatePassword(10, Arrays.asList(
                lowercaseRule,
                uppercaseRule,
                digitRule,
                specialCharRule
        ));
    }
}
