package com.tagg.rediscache;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSha256 {

    public static void main(String[] args) {
        final String test ="0960900016001";
        String sha256hex = Hashing.sha256()
                .hashString(test, StandardCharsets.UTF_8)
                .toString();

        System.out.printf("sha256hex %s%n",sha256hex);

        sha256hex = DigestUtils.sha256Hex(test);
        System.out.printf("sha256hex %s%n",sha256hex);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(test.getBytes(StandardCharsets.UTF_8));
            sha256hex = new String(Hex.encode(hash));
            System.out.printf("sha256hex %s%n",sha256hex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hashbytes = digest.digest(
                    test.getBytes(StandardCharsets.UTF_8));
            String sha3Hex = bytesToHex(hashbytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
