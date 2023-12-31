package es3.server.lib.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 文字列操作に関する色々を定義している．
 */
public class StringOperator {
    /*
     * 文字列をSHA-256でハッシュ化する
     */
    public String sha256Hash(String input) {
        try {
            MessageDigest digest    = MessageDigest.getInstance("SHA-256");
            byte[]        hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /*
     * パスワードを比較する．
     */
    public boolean comparePasswords(String password, String hashedPassword) {
        return sha256Hash(password).equals(hashedPassword);
    }

    /*
     * ISOStringをLocalDateTime型に変換する．
     */
    public LocalDateTime convertIsoToLocalDateTime(String date) {
        Instant instant = Instant.parse(date);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}