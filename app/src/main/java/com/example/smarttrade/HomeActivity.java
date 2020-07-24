package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smarttrade.config.Session;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(Session.getUserVerification()){

            if(Session.getUserType().equals("seller")) {
                Toast.makeText(getApplicationContext(), Session.getUserType(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, SellerMainActivity.class));
            }
            else
                startActivity(new Intent(this, MainActivity.class));

            finish();
        }
        else{

            startActivity(new Intent(HomeActivity.this,  LoginActivity.class));
            finish();
        }
    }
}
