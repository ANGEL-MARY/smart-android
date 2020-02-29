package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smarttrade.config.Session;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(Session.getUserVerification()){
            startActivity(new Intent(this, SellerMainActivity.class));
            finish();
        }
        else{

            startActivity(new Intent(HomeActivity.this,  LoginActivity.class));
            finish();
        }
    }
}
