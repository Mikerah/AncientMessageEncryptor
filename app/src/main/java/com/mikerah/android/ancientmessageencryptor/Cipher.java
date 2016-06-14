package com.mikerah.android.ancientmessageencryptor;

/**
 * Created by Mikerah on 2016-06-13.
 */
public interface Cipher {

    public String encrypt(String key, String message);
    public String decrypt(String key, String message);
}
