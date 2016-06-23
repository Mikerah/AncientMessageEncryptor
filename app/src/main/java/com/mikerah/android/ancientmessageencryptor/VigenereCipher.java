package com.mikerah.android.ancientmessageencryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class VigenereCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = "";
        String encryptionKey = key;
        List<Character> ciphertext = new ArrayList<Character>();
        int keyIndex = 0;

        for(int i=0; i < message.length(); i++) {
            int num = 0;
            if(Character.isUpperCase(message.charAt(i))) {
                num = message.charAt(i) - 'A';
            }
            else if (Character.isLowerCase(message.charAt(i))){
                num = message.charAt(i) - 'a';
            }

            if(Character.isLetter(message.charAt(i))) {
                if(Character.isUpperCase(key.charAt(keyIndex))) {
                    num += key.charAt(keyIndex) - 'A';
                }
                else if (Character.isLowerCase(key.charAt(keyIndex))){
                    num += key.charAt(keyIndex) - 'a';
                }

                num %= 26;

                if (Character.isUpperCase(message.charAt(i))) {
                    ciphertext.add((char) (num + 65));
                }
                else if(Character.isLowerCase(message.charAt(i))){
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

        }

        translated = createString(ciphertext);

        return translated;
    }

    @Override
    public String decrypt(String key, String message) {
        String translated = "";
        String decryptionKey = key;
        List<Character> plaintext = new ArrayList<Character>();
        int keyIndex = 0;

        for(int i=0; i < message.length(); i++) {
            int num = 0;
            if(Character.isUpperCase(message.charAt(i))) {
                num = message.charAt(i) - 'A';
            }
            else if(Character.isLowerCase(message.charAt(i))){
                num = message.charAt(i) - 'a';
            }
            if(Character.isLetter(message.charAt(i))) {

                if(Character.isUpperCase(key.charAt(keyIndex))) {
                    num -= key.charAt(keyIndex) - 'A';
                }
                else if (Character.isLowerCase(key.charAt(keyIndex))){
                    num -= key.charAt(keyIndex) - 'a';
                }

                if(num < 0) {
                    num = num % 26 + 26;
                }
                else {
                    num = num % 26;
                }

                if (Character.isUpperCase(message.charAt(i))) {
                    plaintext.add((char) (num + 65));
                }
                else if(Character.isLowerCase(message.charAt(i))){
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

        }

        translated = createString(plaintext);

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
