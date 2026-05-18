package com.kalyani.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button scanBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scanBtn);
        resultText = findViewById(R.id.resultText);

        scanBtn.setOnClickListener(v -> {

            IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);

            integrator.setPrompt("Scan a QR Code");
            integrator.setBeepEnabled(true);
            integrator.setOrientationLocked(true);

            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (result != null) {

            if (result.getContents() == null) {

                resultText.setText("Cancelled");

            } else {

                resultText.setText(result.getContents());
            }

        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}