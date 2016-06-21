package com.mikerah.android.ancientmessageencryptor;

import java.math.BigInteger;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class AffineCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = "";
        int encryptionKey = Integer.parseInt(key);
        int keyA = (int) Math.floor(encryptionKey / 26);
        int keyB = encryptionKey % 26;

        for(int i = 0; i < message.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                int index = 0;
                if (Character.isUpperCase(message.charAt(i))) {
                    index = message.charAt(i) - 'A';
                    translated += (char)(((index * keyA + keyB) % 26) + 65);
                }
                else if (Character.isLowerCase(message.charAt(i))) {
                    index = message.charAt(i) - 'a';
                    translated += (char)(((index * keyA + keyB) % 26) + 97);
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
        int keyA = (int) Math.floor(decryptionKey / 26);
        int keyB = decryptionKey % 26;
        int modInverse = new BigInteger(String.valueOf(keyA)).modInverse(BigInteger.valueOf(26)).intValue();

        for(int i =0; i < message.length(); i++) {
            if(Character.isLetter(message.charAt(i))) {
                int index = 0;
                if (Character.isUpperCase(message.charAt(i))) {
                    index = message.charAt(i) - 'A';
                    char toAdd = (char) (((((index - keyB) * modInverse) % 26 + 26) % 26) + 65);
                    translated += toAdd;
                }
                else if (Character.isLowerCase(message.charAt(i))) {
                    index = message.charAt(i) - 'a';
                    char toAdd = (char) (((((index - keyB) * modInverse) % 26 + 26) % 26) + 97);
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
