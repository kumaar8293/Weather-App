package com.mukesh.weatherapp.restapi;


import com.mukesh.weatherapp.datamodel.Weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("current")
    Call<Weather> getRequestWithMapQueryQuery(@QueryMap Map<String, Object> params);
}
