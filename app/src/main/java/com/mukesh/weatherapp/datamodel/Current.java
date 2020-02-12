package com.mukesh.weatherapp.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Current implements Serializable {
    @Override
    public String toString() {
        return "Current{" +
                "observationTime='" + observationTime + '\'' +
                ", temperature=" + temperature +
                ", weatherCode=" + weatherCode +
                ", weatherIcons=" + weatherIcons +
                ", weatherDescriptions=" + weatherDescriptions +
                ", windSpeed=" + windSpeed +
                ", windDegree=" + windDegree +
                ", windDir='" + windDir + '\'' +
                ", pressure=" + pressure +
                ", precip=" + precip +
                ", humidity=" + humidity +
                ", cloudcover=" + cloudcover +
                ", feelslike=" + feelslike +
                ", uvIndex=" + uvIndex +
                ", visibility=" + visibility +
                ", isDay='" + isDay + '\'' +
                '}';
    }

    @SerializedName("observation_time")
    @Expose
    private String observationTime;
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
    @SerializedName("weather_code")
    @Expose
    private Integer weatherCode;
    @SerializedName("weather_icons")
    @Expose
    private List<String> weatherIcons = null;
    @SerializedName("weather_descriptions")
    @Expose
    private List<String> weatherDescriptions = null;
    @SerializedName("wind_speed")
    @Expose
    private Integer windSpeed;
    @SerializedName("wind_degree")
    @Expose
    private Integer windDegree;
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("precip")
    @Expose
    private Integer precip;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("cloudcover")
    @Expose
    private Integer cloudcover;
    @SerializedName("feelslike")
    @Expose
    private Integer feelslike;
    @SerializedName("uv_index")
    @Expose
    private Integer uvIndex;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("is_day")
    @Expose
    private String isDay;

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<String> getWeatherIcons() {
        return weatherIcons;
    }

    public void setWeatherIcons(List<String> weatherIcons) {
        this.weatherIcons = weatherIcons;
    }

    public List<String> getWeatherDescriptions() {
        return weatherDescriptions;
    }

    public void setWeatherDescriptions(List<String> weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getPrecip() {
        return precip;
    }

    public void setPrecip(Integer precip) {
        this.precip = precip;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(Integer cloudcover) {
        this.cloudcover = cloudcover;
    }

    public Integer getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(Integer feelslike) {
        this.feelslike = feelslike;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getIsDay() {
        return isDay;
    }

    public void setIsDay(String isDay) {
        this.isDay = isDay;
    }
}
