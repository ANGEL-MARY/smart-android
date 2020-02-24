package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.retrofit.AppClient;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {


    private TextView priceTextView, productNameTextView, productSoldBy;
    private ImageView productImage;
    private Button BuynowButton, addTocartButton;


    private String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        productId =  getIntent().getStringExtra("productId");

        Toolbar toolbar = findViewById(R.id.toolbarDetail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        productImage = findViewById(R.id.productImage);
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
        getProdcut(productId);
    }

    public  void  getProdcut(final String productId){
        DataManager.getDataManager().getProduct(productId, new RetrofitCallBack<Product>() {
            @Override
            public void Success(Product data) {
                productNameTextView.setText(data.getItem().getName());
                String price = data.getPrice();
                if(data.getSellMethod().equals("packet"))
                    price += "Rs / Packet";
                else
                    price += "Rs / Kg";
                priceTextView.setText(price);

                Glide.with(getApplicationContext()).load(AppClient.MASTEERURL+ data.getItem().getImageUrl()).placeholder(R.drawable.shop_placeholder).into(productImage);
            }

            @Override
            public void Failure(String error) {
                Toast.makeText(getApplicationContext(), "Failed to get product details", Toast.LENGTH_LONG).show();
            }
        });
    }
}
