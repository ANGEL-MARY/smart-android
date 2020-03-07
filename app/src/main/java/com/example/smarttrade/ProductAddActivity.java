package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class ProductAddActivity extends AppCompatActivity {

    private RadioGroup radio;
    private RadioButton packetButton, looseButton;
    private Button addButton;
    private EditText productnameEditText, priceEditText, numberofstocksEditText;


    private String productname, price, numberofstocks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        radio = findViewById(R.id.radio);
        packetButton = findViewById(R.id.Packet);
        looseButton = findViewById(R.id.Loose);
        addButton = findViewById(R.id.Add);
        productnameEditText = findViewById(R.id.ProductName);
        priceEditText = findViewById(R.id.PriceName);
        numberofstocksEditText = findViewById(R.id.StockDetails);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productname = productnameEditText.getText().toString();
                price = priceEditText.getText().toString();
                numberofstocks = numberofstocksEditText.getText().toString();


            }
        });


        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                checkedId = R.id.Packet;
                checkedId = R.id.Loose;
            }
        });
    }



    private HashMap<String, String> getproductaddparams(String productname, String price, String numberofstocks) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productname", productname);
        hashMap.put("price", price);
        hashMap.put("numberofstocks", numberofstocks);

        return hashMap;
    }
}

