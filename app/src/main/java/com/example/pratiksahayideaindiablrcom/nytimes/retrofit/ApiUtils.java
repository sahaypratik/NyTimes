package com.example.pratiksahayideaindiablrcom.nytimes.retrofit;

public class ApiUtils
{
    public static final String BASE_URL="https://api.nytimes.com/";

    public static UserService getUserService()
    {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
