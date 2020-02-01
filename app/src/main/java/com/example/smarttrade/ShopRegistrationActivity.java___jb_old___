package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ShopRegistrationActivity extends AppCompatActivity {

    private MaterialButton continueButton;
    private EditText shopnameEditText, storetypeEditText, locationEditText;


    private String shopname, storetype, location;



    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_registration);


        continueButton = findViewById(R.id.shopSave);
        shopnameEditText = findViewById(R.id.shopName);
        storetypeEditText = findViewById(R.id.storeType);
        locationEditText = findViewById(R.id.location);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //To read use getText()
                shopname = shopnameEditText.getText().toString();
                storetype = storetypeEditText.getText().toString();
                location = locationEditText.getText().toString();


                Toast.makeText(getApplicationContext(),"Shopname : "+ shopname, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Storetype: "+ storetype, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Location : "+ location, Toast.LENGTH_LONG).show();


                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });



    }
}
