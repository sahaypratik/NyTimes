package com.example.pratiksahayideaindiablrcom.nytimes.retrofit;

import android.telecom.Call;

import com.example.pratiksahayideaindiablrcom.nytimes.model.RespData;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("svc/topstories/v2/{section}.json")
    retrofit2.Call<RespData> getData(@Path("section") String section, @Query("api-key")String key);
}
