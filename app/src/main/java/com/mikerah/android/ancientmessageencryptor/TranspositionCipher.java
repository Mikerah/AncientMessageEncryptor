package com.mikerah.android.ancientmessageencryptor;

/**
 * Created by Mikerah on 2016-06-13.
 */
public class TranspositionCipher implements Cipher {

    @Override
    public String encrypt(String key, String message) {
        String translated = null;
        int encryptionKey = Integer.parseInt(key);
        char ciphertext[encryptionKey];

        for(int col = 0; col < encryptionKey; col++) {
            int pointer = col;

            while(pointer < message.length()) {
                ciphertext[col] += message.charAt(pointer);

                pointer += encryptionKey;
            }
        }

        translated = new String(ciphertext);

        return translated;
    }

    @Override
    public String decrypt(String key, String message) {
        String translated = null;
        int decryptionKey = Integer.parseInt(key);
        int colsForDecryption = (int) Math.ceil(message.length()/decryptionKey);
        int rowsForDecryption = Integer.parseInt(key);
        int boxes = (colsForDecryption * rowsForDecryption) - message.length();
        char plaintext[colsForDecryption];

        int col = 0, row = 0;

        for(int i=0; i < message.length(); i++) {
            plaintext[col] += message.charAt(i);
            col += 1;

            if ((col == colsForDecryption)|| (col == colsForDecryption - 1 && row >= rowsForDecryption - boxes)) {
                col = 0;
                row += 1;
            }
        }

        translated = new String(plaintext);

        return translated;
    }
}
