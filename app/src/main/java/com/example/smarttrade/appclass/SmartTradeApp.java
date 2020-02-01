package com.example.smarttrade.appclass;

import android.app.Application;

import com.example.smarttrade.R;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.config.Session;
import com.mapbox.mapboxsdk.Mapbox;

public class SmartTradeApp extends Application {

    private  static final String API_KEY ="AIzaSyA3ELJ-2i9B_8M43IvGNkMuQWnlCOQfwaQ";
    @Override
    public void onCreate() {
        super.onCreate();
        Session.getSession(this);

        DataManager.getDataManager().init(this);



        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));
    }
}
