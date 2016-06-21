package com.mikerah.android.ancientmessageencryptor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {

    private Cipher mCipher;
    private String mCipherToUse;
    private String mMode;
    private String mKey;
    private boolean mWantText;
    private boolean mWantEmail;
    private EditText mMessgeToEncrypt;
    private Button mSendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mMessgeToEncrypt = (EditText) findViewById(R.id.message_to_encrypt);
        mSendButton = (Button) findViewById(R.id.send_button);

        Bundle information = getIntent().getExtras();

        mCipherToUse = information.getString("Cipher");
        mMode = information.getString("Mode");
        mKey = information.getString("CipherKey");
        mWantText = information.getBoolean("TextMessageOption");
        mWantEmail = information.getBoolean("EmailOption");

        mCipherToUse = mCipherToUse.replaceAll("\\s","");
        mMode = mMode.replaceAll("\\s","");
        mKey = mKey.replaceAll("\\s","");

        Class typeOfCipher = null;
        try {
            typeOfCipher = Class.forName("com.mikerah.android.ancientmessageencryptor." + mCipherToUse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            mCipher = (Cipher) typeOfCipher.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessgeToEncrypt.getText().toString();

                String toSend = "";
                if(mMode.equals("Encrypt")) {
                    toSend = mCipher.encrypt(mKey,message);
                }
                else if (mMode.equals("Decrypt")) {
                    toSend = mCipher.decrypt(mKey,message);
                }

                if(mWantText) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"));
                    intent.putExtra("sms_body", toSend);
                    startActivity(intent);
                }
                else if (mWantEmail) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,toSend);
                    startActivity(intent);
                }

            }
        });

    }




}
