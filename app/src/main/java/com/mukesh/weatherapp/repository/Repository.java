package com.mukesh.weatherapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mukesh.weatherapp.datamodel.Weather;
import com.mukesh.weatherapp.restapi.ApiClient;
import com.mukesh.weatherapp.restapi.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {

    private static Repository instance;
    private static MutableLiveData<ArrayList<Weather>> weatherDataList;

    private static ArrayList<Weather> weatherList;
    private static MutableLiveData<String> failedMsg;
    private static ApiInterface apiInterface;

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
            failedMsg = new MutableLiveData<>();
            weatherDataList = new MutableLiveData<>();
            weatherList = new ArrayList<>();
            apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        }
        return instance;
    }

    public void loadCurrentWeather(String query) {
        Map<String, Object> data = new HashMap<>();
        data.put("access_key", ApiClient.API_KEY);
        data.put("query", query);
        Call<Weather> call = apiInterface.getRequestWithMapQueryQuery(data);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {

                if (!response.isSuccessful()) {
                    failedMsg.postValue(response.message());
                    return;
                }

                if (response.body() == null || response.body().getLocation()==null){
                    failedMsg.postValue("City Not found");
                    return;
                }
                boolean found = false;
                for (Weather weather : weatherList) {
                    if (weather.getLocation().getName().equalsIgnoreCase(response.body().getLocation().getName())) {
                        found = true;
                    }
                }
                if (!found) {
                    weatherList.add(response.body());
                    weatherDataList.postValue(weatherList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable throwable) {

                failedMsg.postValue(throwable.getMessage());
            }
        });
    }

    public LiveData<String> getErrorMessageListener() {
        return failedMsg;
    }

    public LiveData<ArrayList<Weather>> getWeatherDetails() {
        return weatherDataList;
    }

}
