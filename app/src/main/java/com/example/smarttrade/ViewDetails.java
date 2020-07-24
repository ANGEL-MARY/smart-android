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
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.Order;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.retrofit.AppClient;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {


    private TextView priceTextView, productNameTextView, productSoldBy;
    private ImageView productImage;
    private Button BuynowButton, addTocartButton;

    private  Product product;
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
                if(product != null)
                DataManager.getDataManager().addCart(getCartParams(product.getId(), product.getPrice()), new RetrofitCallBack<Cart>() {
                    @Override
                    public void Success(Cart data) {
                        Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void Failure(String error) {

                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        BuynowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(product!= null){
                    DataManager.getDataManager().buyNow(getParams(product.getId(), product.getPrice()), new RetrofitCallBack<Order>() {
                        @Override
                        public void Success(Order data) {

                            Toast.makeText(getApplicationContext(), "Order dispatched", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void Failure(String error) {
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        });
        getProduct(productId);
    }


    private HashMap<String, String> getCartParams(String product, String current_price) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product", product);
        hashMap.put("current_price", current_price);
        return hashMap;
    }

    private HashMap<String, String> getParams(String productId , String currentPrice) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product", productId);
        hashMap.put("current_price", currentPrice);

        return hashMap;
    }

    public  void  getProduct(final String productId){
        DataManager.getDataManager().getProduct(productId, new RetrofitCallBack<Product>() {
            @Override
            public void Success(Product data) {
                productNameTextView.setText(data.getItem().getName());
                if(data != null)
                    product = data;
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
