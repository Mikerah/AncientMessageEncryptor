package com.mikerah.android.ancientmessageencryptor;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class CaesarCipher implements Cipher {
    @Override
    public String encrypt(String key, String message) {
        String translated = "";
        int encryptionKey = Integer.parseInt(key);

        for(int i = 0; i < message.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                if(Character.isUpperCase(message.charAt(i))) {
                    int num = message.charAt(i) - 'A';
                    num += encryptionKey;

                    if (num >= 26) {
                        num -= 26;
                    }

                    translated += (char)(num + 65);
                }
                else {
                    int num = message.charAt(i) - 'a';
                    num += encryptionKey;

                    if (num >= 26) {
                        num -= 26;
                    }

                    translated += (char)(num + 97);
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
        String translated = "";
        int decryptionKey = Integer.parseInt(key);

        for(int i = 0; i < message.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                if(Character.isUpperCase(message.charAt(i))) {
                    int num = message.charAt(i) - 'A';

                    num -= decryptionKey;

                    if (num < 0) {

                        num += 26;
                    }

                    char toAdd = (char)(num + 65);
                    translated += toAdd;
                }
                else {
                    int num = message.charAt(i) - 'a';

                    num -= decryptionKey;

                    if (num < 0) {
                        num += 26;
                    }

                    char toAdd = (char)(num + 97);
                    translated += toAdd;
                }

            }
            else {
                translated += message.charAt(i);
            }

        }

        return translated;
    }
}
