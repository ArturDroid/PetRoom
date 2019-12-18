package com.ardroid.petroom.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIProvider {
    private static NasaAPI NASA_API = null;
    private static final String BASE_URL = "https://api.nasa.gov/";
    public static final String API_KEY = "FxJKkfHLOuF47iWVggsJnXArRBvsVSCPv69eug3B";


    public static NasaAPI getNasaAPI(){
        if(NASA_API == null){
            NASA_API = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NasaAPI.class);
        }
        return NASA_API;
    }
}
