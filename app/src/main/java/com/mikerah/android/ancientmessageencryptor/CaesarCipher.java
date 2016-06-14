package com.mikerah.android.ancientmessageencryptor;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class CaesarCipher implements Cipher {
    @Override
    public String encrypt(String key, String message) {
        String translated = null;
        int encryptionKey = Integer.parseInt(key);

        for(int i = 0; i < translated.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                if(Character.isUpperCase(message.charAt(i))) {
                    int num = message.charAt(i) - 'A';
                    num += encryptionKey;

                    if (num >= 26) {
                        num -= 26;
                    }
                    else {
                        num += 26;
                    }

                    translated += (num + 65);
                }
                else {
                    int num = message.charAt(i) - 'a';
                    num += encryptionKey;

                    if (num >= 26) {
                        num -= 26;
                    }

                    translated += (num + 97);
                }

            }
            else {
                translated += message.charAt(i);
            }

        }

        return translated;

    }

    @Override
    public String decrypt(String key, String message) {
        String translated = null;
        int encryptionKey = Integer.parseInt(key);

        for(int i = 0; i < translated.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                if(Character.isUpperCase(message.charAt(i))) {
                    int num = message.charAt(i) - 'A';
                    num -= encryptionKey;

                    if (num >= 26) {
                        num -= 26;
                    }

                    translated += (num + 65);
                }
                else {
                    int num = message.charAt(i) - 'a';
                    num += encryptionKey;

                    if (num < 0) {
                        num += 26;
                    }

                    translated += (num + 97);
                }

            }
            else {
                translated += message.charAt(i);
            }

        }

        return translated;
    }
}
