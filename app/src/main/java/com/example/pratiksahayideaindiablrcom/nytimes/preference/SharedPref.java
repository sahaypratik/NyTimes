package com.example.pratiksahayideaindiablrcom.nytimes.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {


    public static String getData(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPref = context.getSharedPreferences("token",
                    Context.MODE_PRIVATE);
            return sharedPref.getString(key, null);
        } else {
            return null;
        }

    }

    public static void setData(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences("token",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setBoolean(Context context, String key, Boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences("token",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPref = context.getSharedPreferences("token",
                    Context.MODE_PRIVATE);
            return sharedPref.getBoolean(key, false);
        } else {
            return false;
        }

    }
}

