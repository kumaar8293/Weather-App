package com.mukesh.weatherapp.restapi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String API_KEY = "c9116beb02bcdc66717deaad61b9c7a3";
    private static final String BASE_URL = "http://api.weatherstack.com/";
    /**
     * Per the release notes, OkHttp requires that you enable Java 8
     * in your builds to function as of 3.13 and newer.
     * You can learn more about how to enable this at
     * https://developer.android.com/studio/write/java8-support.
     */
    private static Retrofit retrofit;
    //BASE url must be end with / else it will give illegal exception

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient okHttpClient = new OkHttpClient
                            .Builder()
                            .addInterceptor(loggingInterceptor)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }
}