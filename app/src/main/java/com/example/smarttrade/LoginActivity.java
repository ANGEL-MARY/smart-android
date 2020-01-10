package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton loginButton;
    private EditText nameEditText, phonEditText;


    private String phone, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Initialize components
        loginButton = findViewById(R.id.loginButton);
        nameEditText= findViewById(R.id.name);
        phonEditText = findViewById(R.id.phone);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplication(), OtpVerificationActivity.class));
//                finish();
                //To read use getText()
                name = nameEditText.getText().toString();
                phone = phonEditText.getText().toString();


                Toast.makeText(getApplicationContext(),"Name : "+ name, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Phone : "+ phone, Toast.LENGTH_LONG).show();

            }
        });

    }
}
