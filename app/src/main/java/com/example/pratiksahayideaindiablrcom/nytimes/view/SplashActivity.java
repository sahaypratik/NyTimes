package com.example.pratiksahayideaindiablrcom.nytimes.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pratiksahayideaindiablrcom.nytimes.R;
import com.example.pratiksahayideaindiablrcom.nytimes.constants.Constants;
import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;
import com.example.pratiksahayideaindiablrcom.nytimes.model.RespData;
import com.example.pratiksahayideaindiablrcom.nytimes.preference.SharedPref;
import com.example.pratiksahayideaindiablrcom.nytimes.retrofit.ApiUtils;
import com.example.pratiksahayideaindiablrcom.nytimes.retrofit.UserService;
import com.example.pratiksahayideaindiablrcom.nytimes.room.MyAppDatabase;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (SharedPref.getBoolean(SplashActivity.this,"firstTime"))
        {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else if (!SharedPref.getBoolean(SplashActivity.this,"firstTime"))
        {
            for (int i=0;i<sec.size();i++)
            {
                fetchData(sec.get(i),SplashActivity.this);
            }
        }

    }


}
