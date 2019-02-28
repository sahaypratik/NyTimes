package com.example.pratiksahayideaindiablrcom.nytimes.view;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.pratiksahayideaindiablrcom.nytimes.CustomDialog;
import com.example.pratiksahayideaindiablrcom.nytimes.constants.Constants;
import com.example.pratiksahayideaindiablrcom.nytimes.listener.UpdateInfo;
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

public class BaseActivity extends AppCompatActivity {
    UserService userService;
    HashMap<String, List<DataItem>> hashMap;
    List<String> sec;
    public static MyAppDatabase myAppDatabase;
    public boolean sync_now = false;
    CustomDialog customDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userService = ApiUtils.getUserService();
        hashMap = new HashMap<>();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "datadb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        sec = new ArrayList<>();
        sec.add("science");
        sec.add("technology");
        sec.add("business");
        sec.add("world");
        sec.add("movies");
        sec.add("travel");

    }

    public void fetchData(final String section, final Context context) {
        // showDialog();
        Call<RespData> call = userService.getData(section, Constants.KEY);
        call.enqueue(new Callback<RespData>() {
            @Override
            public void onResponse(Call<RespData> call, Response<RespData> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    RespData respData = response.body();
                    List<DataItem> dm = new ArrayList<>();
                    for (int i = 0; i < respData.getResults().size(); i++) {
                        DataItem dataItem = new DataItem();
                        dataItem.setSection(section);
                        dataItem.setJsonMemberAbstract(respData.getResults().get(i).getJsonMemberAbstract());
                        dataItem.setTitle(respData.getResults().get(i).getTitle());
                        dataItem.setUrl(respData.getResults().get(i).getUrl());
                        dataItem.setShortUrl(respData.getResults().get(i).getShortUrl());
                        dataItem.setMultimedia(respData.getResults().get(i).getMultimedia());
                        dataItem.setByline(respData.getResults().get(i).getByline());
                        dataItem.setPublishedDate(respData.getResults().get(i).getPublishedDate());
                        dm.add(dataItem);
                    }

                   // Toast.makeText(context, section, Toast.LENGTH_SHORT).show();
                    hashMap.put(section, dm);
                    if (hashMap.size() == sec.size()) {
                        for (int i = 0; i < sec.size(); i++) {
                            myAppDatabase.myDao().addData(hashMap.get(sec.get(i)));
                            Log.i("section", sec.get(i));

                        }
                        if (sync_now)
                        {
                            MainActivity.getInstance().updateList();
                        }
                        else {
                            SharedPref.setBoolean(context, "firstTime", true);
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<RespData> call, Throwable t) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
