package com.example.weatherapp.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Request implements Serializable {
    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", query='" + query + '\'' +
                ", language='" + language + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getType() {
        return type;
    }

    public String getQuery() {
        return query;
    }

    public String getLanguage() {
        return language;
    }

    public String getUnit() {
        return unit;
    }

}
