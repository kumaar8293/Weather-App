package com.example.weatherapp.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Location implements Serializable {
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", timezoneId='" + timezoneId + '\'' +
                ", localtime='" + localtime + '\'' +
                ", localtimeEpoch=" + localtimeEpoch +
                ", utcOffset='" + utcOffset + '\'' +
                '}';
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("timezone_id")
    @Expose
    private String timezoneId;
    @SerializedName("localtime")
    @Expose
    private String localtime;
    @SerializedName("localtime_epoch")
    @Expose
    private Integer localtimeEpoch;
    @SerializedName("utc_offset")
    @Expose
    private String utcOffset;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getLat() {
        return lat;
    }


    public String getLon() {
        return lon;
    }


    public String getTimezoneId() {
        return timezoneId;
    }


    public String getLocaltime() {
        return localtime;
    }

    public Integer getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

}
