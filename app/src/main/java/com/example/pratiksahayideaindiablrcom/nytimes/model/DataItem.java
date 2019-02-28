package com.example.pratiksahayideaindiablrcom.nytimes.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.pratiksahayideaindiablrcom.nytimes.converter.MultimediaConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "dataitem")
public class DataItem implements Serializable
{


    @ColumnInfo(name = "section")
    private String section;

    @ColumnInfo(name = "abstract")
    private String jsonMemberAbstract;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "shorturl")
    private String shortUrl;

    @TypeConverters(MultimediaConverter.class)
    @ColumnInfo(name = "multimedia")
    private List<MultimediaItem> multimedia;

    @ColumnInfo(name = "byline")
    private String byline;

    @ColumnInfo(name = "publishdate")
    private String publishedDate;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }



    public String getJsonMemberAbstract() {
        return jsonMemberAbstract;
    }

    public void setJsonMemberAbstract(String jsonMemberAbstract) {
        this.jsonMemberAbstract = jsonMemberAbstract;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public List<MultimediaItem> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultimediaItem> multimedia) {
        this.multimedia = multimedia;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
