package com.example.pratiksahayideaindiablrcom.nytimes.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;

import java.util.List;

@Dao
public interface MyDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addData(List<DataItem> dataItem);

    @Query("select * from dataitem")
    public List<DataItem> getUsers();

    @Query("select * from dataitem where section=:sec")
    public List<DataItem> getSpecificData(String sec);

}
