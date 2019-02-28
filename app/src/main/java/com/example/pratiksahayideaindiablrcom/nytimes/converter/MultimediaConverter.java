package com.example.pratiksahayideaindiablrcom.nytimes.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.pratiksahayideaindiablrcom.nytimes.model.MultimediaItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MultimediaConverter {

    @TypeConverter()
    public String fromTechniquesItemList(List<MultimediaItem> techniquesItems){
        if (techniquesItems == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MultimediaItem>>() {
        }.getType();
        String json = gson.toJson(techniquesItems, type);
        return json;
    }

    @TypeConverter()
    public List<MultimediaItem> toTechniquesItemList(String techniquesItemString){
        if (techniquesItemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MultimediaItem>>() {
        }.getType();
        List<MultimediaItem> techniquesItemList = gson.fromJson(techniquesItemString, type);
        return techniquesItemList;
    }

}
