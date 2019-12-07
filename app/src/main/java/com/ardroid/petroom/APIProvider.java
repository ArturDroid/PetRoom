package com.ardroid.petroom;

import retrofit2.Retrofit;

public class APIProvider {

    private static String url = "http://numbersapi.com/";

    private static NumbersAPI numbersAPI = null;
    private static final String baseUrl  = url;
    public static NumbersAPI getInstance(){
        if(numbersAPI == null){
            numbersAPI = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .build()
                    .create(NumbersAPI.class);
        }
        return numbersAPI;

    }

    public static void setUrl(String url) {
        APIProvider.url = url;
    }


}

