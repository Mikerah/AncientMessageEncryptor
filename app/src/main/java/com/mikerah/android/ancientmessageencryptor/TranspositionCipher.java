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
        /*
        int colsForDecryption = (int) Math.ceil(message.length()/decryptionKey);
        int rowsForDecryption = Integer.parseInt(key);
        int boxes = (colsForDecryption * rowsForDecryption) - message.length();
        */
        Character [] plaintext = new Character[message.length()];

        /*
        int col = message.length() / decryptionKey;
        for(int i = 0; i < col; i++) {
            for(int j = i; j < message.length(); j += i) {
                plaintext.add(message.charAt(j));
            }
        }
        */

        /*
        int k = 0;
        for(int col = 0; col < decryptionKey; col++) {
            for (int i = col; i < message.length(); i += decryptionKey) {
                plaintext.add(message.charAt(col+1));
            }
        }
        */

        // int col = 0, row = 0;


        int k = 0;

        for(int col = 0; col < decryptionKey; col++) {
            for(int i = col; i < message.length(); i += decryptionKey) {
                plaintext[i] = message.charAt(k++);
            }
        }


        /*
        for(int i=0; i < message.length(); i++) {
            char letter = message.charAt(i);
            plaintext.add(col, letter);
            col += 1;

            if ((col == colsForDecryption)|| (col == colsForDecryption - 1 && row >= rowsForDecryption - boxes)) {
                col = 0;
                row += 1;
            }
        }
        */

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
