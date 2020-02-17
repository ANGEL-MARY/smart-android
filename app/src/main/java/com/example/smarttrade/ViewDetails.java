package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Product;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {


    private EditText priceEditText, sellerDetailsEditText, quantityEditText, nameEditText, sell_methodEditText;
    private Button BuynowButton, addTocartButton;


    private String price, sellerDetails, quantity, name, sell_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        BuynowButton = findViewById(R.id.button);
        addTocartButton = findViewById(R.id.addTo);
        priceEditText = findViewById(R.id.price);
        sellerDetailsEditText = findViewById(R.id.sellerDetails);
        quantityEditText = findViewById(R.id.quantity);
        nameEditText = findViewById(R.id.name);

        addTocartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BuynowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                price = priceEditText.getText().toString();
                sellerDetails = sellerDetailsEditText.getText().toString();
                quantity = quantityEditText.getText().toString();
                name = nameEditText.getText().toString();
                sell_method = sell_methodEditText.getText().toString();



                DataManager.getDataManager().productRegistration(getproductparams(price, sellerDetails, quantity, name, sell_method), new RetrofitCallBack<Product>() {
                    @Override
                    public void Success(Product data) {

                        startActivity(new Intent(getApplication(),MainActivity.class));
                    }

                    @Override
                    public void Failure(String error) {

                    }
                });

            }

        });
    }


    private HashMap<String, String> getproductparams(String price, String sellerDetails, String quantity, String name, String sell_method) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("price", price);
        hashMap.put("sellerDetails", sellerDetails);
        hashMap.put("quantity", quantity);
        hashMap.put("name", name);
        hashMap.put("sell_method", sell_method);


        return hashMap;
    }
}
