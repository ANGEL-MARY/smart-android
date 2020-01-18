package com.example.smarttrade.config;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private static final String PREFERENCE = "Preference";

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void getSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setAccessToken(String token) {
        editor.putString("access_token", token).apply();
    }

    public static String getAccessToken() {
        return sharedPreferences.getString("access_token", "");
    }

    public static void setRefreshToken(String token) {
        editor.putString("refresh_token", token).apply();
    }

    public static String getRefreshToken() {
        return sharedPreferences.getString("refresh_token", "");
    }


    public static void setUserVerification(boolean status) {
        editor.putBoolean("verification", status).apply();
    }

    public static boolean getUserVerification() {
        return sharedPreferences.getBoolean("verification", false);
    }

}
