package com.example.weatherapp.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("request")
    @Expose
    private Request request;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("current")
    @Expose
    private Current current;

    public Request getRequest() {
        return request;
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "request=" + request +
                ", location=" + location +
                ", current=" + current +
                '}';
    }
}
