package com.example.smarttrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smarttrade.config.Constants;

public class OtpVerificationActivity extends AppCompatActivity {



    private BroadcastReceiver broadcastReceiver;

    private boolean verifyCalled = false;
    private boolean broadcastReceivedFlag = false;


    private EditText edtTxtOTP1, edtTxtOTP2, edtTxtOTP3, edtTxtOTP4;
    private TextView txtVwWrongNo;
    private TextView phoneTextView;
    private String userOTP = "";


    private String phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        if(getIntent().hasExtra("phone")){
            phone =  getIntent().getStringExtra("phone");
        }

        // Initialize components

        edtTxtOTP1 = (EditText) findViewById(R.id.edtTxtOTP1);
        edtTxtOTP2 = (EditText) findViewById(R.id.edtTxtOTP2);
        edtTxtOTP3 = (EditText) findViewById(R.id.edtTxtOTP3);
        edtTxtOTP4 = (EditText) findViewById(R.id.edtTxtOTP4);
        txtVwWrongNo = (TextView) findViewById(R.id.txtVwWrongNo);
        phoneTextView = findViewById(R.id.phoneNumber);

        phoneTextView.setText(phone);

        txtVwWrongNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OtpVerificationActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        //Edit text controllers
        edtTxtOTP1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP1.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        edtTxtOTP2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP2.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        edtTxtOTP3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP3.getText().toString().length() == 1)     //size as per your requirement
                {
                    edtTxtOTP4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        edtTxtOTP4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTxtOTP4.getText().toString().length() == 1 )     //size as per your requirement
                {
                    verifyUserOTP();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void verifyUserOTP(){
        startActivity(new Intent(this,EntryActivity.class ));
    }





    @Override
    protected void onStart() {
        super.onStart();

        registerBroadcast();
    }

    private void registerBroadcast() {

        Log.d(Constants.APP_NAME, "registerBroadcast ");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (intent.getAction()) {
                    case Constants.SEND_SMS:
                        Log.d(Constants.APP_NAME, "otp Received: "+intent.getStringExtra("sign_otp"));
                        userOTP = intent.getStringExtra("sign_otp");
                        broadcastReceivedFlag = true;
                        setLoginOTP();
                        break;

                }

            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.SEND_SMS);
        registerReceiver(broadcastReceiver, filter);

    }

    private void setLoginOTP() {

        if (!TextUtils.isEmpty(userOTP) && userOTP.length() == 4) {

            for (int i = 0; i < userOTP.length(); i++) {
                String data = String.valueOf(userOTP.charAt(i));

                switch (i) {
                    case 0:
                        edtTxtOTP1.setText(data);
                        break;
                    case 1:
                        edtTxtOTP2.setText(data);
                        break;
                    case 2:
                        edtTxtOTP3.setText(data);
                        break;
                    case 3:
                        edtTxtOTP4.setText(data);
                        break;
                }
            }

            verifyUserOTP();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}
