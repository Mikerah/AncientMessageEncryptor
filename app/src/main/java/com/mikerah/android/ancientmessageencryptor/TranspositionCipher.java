package com.mikerah.android.ancientmessageencryptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class TranspositionCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = "";
        int encryptionKey = Integer.parseInt(key);
        List<Character> ciphertext = new ArrayList<>();

        for(int col = 0; col < encryptionKey; col++) {
            for(int i = col; i < message.length(); i += encryptionKey) {
                ciphertext.add(message.charAt(i));
            }

        }

        translated = createString(ciphertext);

        return translated;
    }

    @Override
    public String decrypt(String key, String message) {
        String translated = "";
        int decryptionKey = Integer.parseInt(key);
        Character [] plaintext = new Character[message.length()];


        int k = 0;

        for(int col = 0; col < decryptionKey; col++) {
            for(int i = col; i < message.length(); i += decryptionKey) {
                plaintext[i] = message.charAt(k++);
            }
        }

        translated = createString(Arrays.asList(plaintext));

        return translated;
    }

    private String createString(List<Character> array) {
        StringBuilder stringBuilder = new StringBuilder(array.size());
        for(Character c: array) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
