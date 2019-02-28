package com.example.pratiksahayideaindiablrcom.nytimes.view;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pratiksahayideaindiablrcom.nytimes.CustomDialog;
import com.example.pratiksahayideaindiablrcom.nytimes.adapters.DataDisplayAdapter;
import com.example.pratiksahayideaindiablrcom.nytimes.listener.ItemSelectListner;
import com.example.pratiksahayideaindiablrcom.nytimes.preference.SharedPref;
import com.example.pratiksahayideaindiablrcom.nytimes.room.MyAppDatabase;
import com.example.pratiksahayideaindiablrcom.nytimes.R;
import com.example.pratiksahayideaindiablrcom.nytimes.constants.Constants;
import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;
import com.example.pratiksahayideaindiablrcom.nytimes.model.RespData;
import com.example.pratiksahayideaindiablrcom.nytimes.retrofit.ApiUtils;
import com.example.pratiksahayideaindiablrcom.nytimes.retrofit.UserService;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity{


    @BindView(R.id.spin)
    Spinner spinner;

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    String content;
    CustomDialog customDialog;
    DataDisplayAdapter dataDisplayAdapter;
    ArrayAdapter<String> arrayAdapter;
    int c=0;
    private List<DataItem> dataItems;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivity=this;
        setSpinner();
        setRvAdapter();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               content=parent.getItemAtPosition(position).toString();
                if (c>0)
                {
                    List<DataItem> dataItems= myAppDatabase.myDao().getSpecificData(parent.getItemAtPosition(position).toString());
                    dataDisplayAdapter.filter(dataItems);
                    recyclerView.smoothScrollToPosition(0);
                }
                c++;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public static MainActivity getInstance(){
        return mainActivity;
    }

    private void setRvAdapter()
    {
        dataItems= myAppDatabase.myDao().getSpecificData(sec.get(0));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        dataDisplayAdapter = new DataDisplayAdapter(MainActivity.this,dataItems, new ItemSelectListner() {
            @Override
            public void onSelect(DataItem data) {
                Intent intent=new Intent(MainActivity.this,DataDetailsActivity.class);
                intent.putExtra("LIST",(Serializable) data);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(dataDisplayAdapter);
    }

    //setting spinner
    private void setSpinner()
    {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sec);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.bt_sync)
    public void onClickOfSync()
    {
        Toast.makeText(MainActivity.this,"Syncing data........Please wait!!",Toast.LENGTH_LONG).show();
        sync_now=true;
        hashMap.clear();
        for (int i=0;i<sec.size();i++)
        {
            fetchData(sec.get(i),MainActivity.this);
        }

    }

    public  void updateList()
    {
         dataItems.clear();
         dataItems= myAppDatabase.myDao().getSpecificData(content);
        if (dataItems.size()>0) {
            Toast.makeText(getInstance(), "Sync successfull", Toast.LENGTH_SHORT).show();
            getInstance().dataDisplayAdapter.filter(dataItems);
            getInstance().recyclerView.smoothScrollToPosition(0);
        }


        }




}
