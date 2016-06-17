package com.mikerah.android.ancientmessageencryptor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AncientEncryptorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ancient_encryptor);

        final Spinner cipherSpinner = (Spinner) findViewById(R.id.cipher_spinner);
        ArrayAdapter<CharSequence> cipherAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.ciphers,
                android.R.layout.simple_spinner_dropdown_item);
        cipherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cipherSpinner.setAdapter(cipherAdapter);

        final Spinner modeSpinner = (Spinner) findViewById(R.id.mode_spinner);
        ArrayAdapter<CharSequence> modeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.modes,
                android.R.layout.simple_spinner_dropdown_item);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);

        final EditText key = (EditText) findViewById(R.id.cipher_key);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cipher = cipherSpinner.getSelectedItem().toString();
                String mode = modeSpinner.getSelectedItem().toString();
                String cipherKey = key.getText().toString();

                Intent intent = new Intent(getBaseContext(), MessageActivity.class);
                intent.putExtra("Cipher", cipher);
                intent.putExtra("Mode", mode);
                intent.putExtra("CipherKey", cipherKey);
                startActivity(intent);
            }
        });


    }
}
