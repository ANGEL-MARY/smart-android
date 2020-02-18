package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Product;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {


    private TextView priceTextView, productNameTextView;
    private Button BuynowButton, addTocartButton;


    private String price, sellerDetails, quantity, name, sell_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BuynowButton = findViewById(R.id.button);
        addTocartButton = findViewById(R.id.addTo);
        priceTextView = findViewById(R.id.productDetailsPrice);
        productNameTextView = findViewById(R.id.productDetailsName);


        addTocartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BuynowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


            }

        });
    }
}
