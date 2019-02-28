package com.example.pratiksahayideaindiablrcom.nytimes.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.pratiksahayideaindiablrcom.nytimes.model.DataItem;
import com.example.pratiksahayideaindiablrcom.nytimes.room.MyDao;

@Database(entities = {DataItem.class},version = 2,exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase
{
    public abstract MyDao myDao();
}
