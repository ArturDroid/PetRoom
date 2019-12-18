package com.ardroid.petroom.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIProvider {
    private static NasaAPI NASA_API = null;
    private static Gson GSON = null;
    private static final String BASE_URL = "https://api.nasa.gov/";
    public static final String API_KEY = "FxJKkfHLOuF47iWVggsJnXArRBvsVSCPv69eug3B";
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    public static NasaAPI getNasaAPI(){
        if(NASA_API == null){
            GSON = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            NASA_API = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GSON))
                    .build()
                    .create(NasaAPI.class);
        }
        return NASA_API;
    }
}
