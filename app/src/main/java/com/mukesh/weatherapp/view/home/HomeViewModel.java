package com.mukesh.weatherapp.view.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mukesh.weatherapp.datamodel.Weather;
import com.mukesh.weatherapp.repository.Repository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private Repository repository;
    private LiveData<ArrayList<Weather>> currentWeatherDetail;
    private LiveData<String> errorMessage;

    LiveData<ArrayList<Weather>> getCurrentWeather() {
        if (currentWeatherDetail == null) {
            repository = Repository.getInstance();
            currentWeatherDetail = repository.getWeatherDetails();
            errorMessage = repository.getErrorMessageListener();
        }
        return currentWeatherDetail;
    }

    LiveData<String> getErrorMessageListener() {
        return errorMessage;
    }

    public void searchWeatherByCity(String query) {
        repository.loadCurrentWeather(query);
    }
}
