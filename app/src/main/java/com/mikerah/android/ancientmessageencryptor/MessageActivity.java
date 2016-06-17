package com.mikerah.android.ancientmessageencryptor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Cipher cipher = null;

        String cipherToUse = null;
        String mode = null;
        String key = null;

        boolean wantText = false;
        boolean wantEmail = false;

        Bundle information = getIntent().getExtras();

        cipherToUse = information.getString("Cipher");
        mode = information.getString("Mode");
        key = information.getString("CipherKey");
        wantText = information.getBoolean("TextMessageOption");
        wantEmail = information.getBoolean("EmailOption");

        cipherToUse = cipherToUse.replaceAll("\\s","");
        mode = mode.replaceAll("\\s","");
        key = key.replaceAll("\\s","");

        Class typeOfCipher = null;
        try {
            typeOfCipher = Class.forName("com.mikerah.android.ancientmessageencryptor." + cipherToUse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            cipher = (Cipher) typeOfCipher.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        final String message = ((EditText) findViewById(R.id.message_to_encrypt)).toString();
        String toSend = null;
        if(mode.equals("Encrypt")) {
            toSend = cipher.encrypt(key,message);
        }
        else if (mode.equals("Decrypt")) {
            toSend = cipher.decrypt(key,message);
        }

        Button sendButton = (Button) findViewById(R.id.send_button);
        final String finalToSend = toSend;
        final boolean finalWantText = wantText;
        final boolean finalWantEmail = wantEmail;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(finalWantText) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"));
                    intent.putExtra("sms_body", finalToSend);
                    startActivity(intent);
                }
                else if (finalWantEmail) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,finalToSend);
                    startActivity(intent);
                }

            }
        });
    }
}
