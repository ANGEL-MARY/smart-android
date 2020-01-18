package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SellerActivity extends AppCompatActivity {

    private MaterialButton continueButton;
    private EditText sellerdeliverrangeEditText,  sellerlocationEditText;


    private String sellerdeliverrange,  sellerlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        continueButton = findViewById(R.id.seller);
        sellerdeliverrangeEditText = findViewById(R.id.sellerDeliverRange);
        sellerlocationEditText = findViewById(R.id.sellerLocation);

        continueButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                sellerdeliverrange = sellerdeliverrangeEditText.getText().toString();
                sellerlocation = sellerlocationEditText.getText().toString();


                Toast.makeText(getApplicationContext(), "Sellerdeliverrange : " + sellerdeliverrange, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "SellerLocation : " + sellerlocation, Toast.LENGTH_LONG).show();


                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });

    }
}
