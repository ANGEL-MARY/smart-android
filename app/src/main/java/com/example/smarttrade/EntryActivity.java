package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EntryActivity extends AppCompatActivity {

    private AppCompatImageButton buyButton;
    private AppCompatImageButton sellButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        buyButton = findViewById(R.id.buy);
        sellButton = findViewById(R.id.sell);

        buyButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             startActivity(new Intent(EntryActivity.this,ShopRegistrationActivity.class));

                                         }
                                     });
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryActivity.this,SellerActivity.class));
            }
        });



    }
}
