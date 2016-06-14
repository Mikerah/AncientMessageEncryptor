package com.mikerah.android.ancientmessageencryptor;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class AffineCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = null;
        int encryptionKey = Integer.parseInt(key);
        int keyA = (int) Math.floor(encryptionKey / 26);
        int keyB = encryptionKey % 26;

        for(int i = 0; i < message.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                int index = 0;
                if (Character.isUpperCase(message.charAt(i))) {
                    index = message.charAt(i) - 'A';
                    translated += (index + 65);
                }
                else if (Character.isLowerCase(message.charAt(i))) {
                    index = message.charAt(i) - 'a';
                    translated += (index + 97);
                }
                else {
                    translated += message.charAt(i);
                }
            }
        }

        return translated;
    }

    @Override
    public String decrypt(String key, String message) {
        String translated = null;
        return translated;
    }
}
