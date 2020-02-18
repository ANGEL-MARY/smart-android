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
import android.widget.Toast;

import com.example.smarttrade.config.Constants;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.config.Session;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Auth;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

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
                    verifyUserOTP(edtTxtOTP1.getText().toString()+edtTxtOTP2.getText().toString()+edtTxtOTP3.getText().toString()+edtTxtOTP4.getText().toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void verifyUserOTP(String otp){
        DataManager.getDataManager().verifyUser(getVerifyParams(phone, otp), new RetrofitCallBack<Auth>() {
            @Override
            public void Success(Auth data) {
                Session.setAccessToken(data.getAccessToken());
                Session.setName(data.getUser().getName());
                Session.setUserVerification(true);
                DataManager.getDataManager().init(OtpVerificationActivity.this);
                if(data.getUser().getType()!= null){
                    String userType = data.getUser().getType();
                    Session.setUserType(userType);
                    if(userType.equals("seller"))
                        startActivity(new Intent(OtpVerificationActivity.this, MainActivity.class));
                    else
                        startActivity(new Intent(OtpVerificationActivity.this, MainActivity.class));
                }else
                    startActivity(new Intent(OtpVerificationActivity.this, EntryActivity.class));
            }

            @Override
            public void Failure(String error) {

            }
        });

    }


    private HashMap<String, String> getVerifyParams(String phoneNumber, String otp) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", phoneNumber);
        hashMap.put("otp", otp);
        return hashMap;
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

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}
