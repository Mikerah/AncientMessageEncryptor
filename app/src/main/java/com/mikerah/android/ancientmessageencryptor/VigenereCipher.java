package com.mikerah.android.ancientmessageencryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class VigenereCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = null;
        String encryptionKey = key;
        List<Character> ciphertext = new ArrayList<Character>();
        int keyIndex = 0;

        for(int i=0; i < message.length(); i++) {
            int num = 0;
            if(num != -1) {
                if(Character.isUpperCase(message.charAt(i))) {
                    num = message.charAt(i) - 'A';
                }
                else {
                    num = message.charAt(i) - 'a';
                }
                if(Character.isUpperCase(key.charAt(keyIndex))) {
                    num += key.charAt(keyIndex) - 'A';
                }
                else {
                    num += key.charAt(keyIndex) - 'a';
                }

                num %= 26;

                if (Character.isUpperCase(message.charAt(i))) {
                    ciphertext.add((char) (num + 65));
                }
                else {
                    ciphertext.add((char) (num + 97));
                }

                keyIndex += 1;
                if(keyIndex == key.length()) {
                    keyIndex = 0;
                }
            }
            else {
                ciphertext.add(message.charAt(i));
            }

            translated = new String(String.valueOf(ciphertext));
        }

        return translated;
    }

    @Override
    public String decrypt(String key, String message) {
        String translated = null;
        String decryptionKey = key;
        List<Character> plaintext = new ArrayList<Character>();
        int keyIndex = 0;

        for(int i=0; i < message.length(); i++) {
            int num = 0;
            if(num != -1) {
                if(Character.isUpperCase(message.charAt(i))) {
                    num = message.charAt(i) - 'A';
                }
                else {
                    num = message.charAt(i) - 'a';
                }
                if(Character.isUpperCase(key.charAt(keyIndex))) {
                    num -= key.charAt(keyIndex) - 'A';
                }
                else {
                    num -= key.charAt(keyIndex) - 'a';
                }

                num %= 26;

                if (Character.isUpperCase(message.charAt(i))) {
                    plaintext.add((char) (num + 65));
                }
                else {
                    plaintext.add((char) (num + 97));
                }

                keyIndex += 1;
                if(keyIndex == key.length()) {
                    keyIndex = 0;
                }
            }
            else {
                plaintext.add(message.charAt(i));
            }

            translated = new String(String.valueOf(plaintext));
        }

        return translated;
    }
}
