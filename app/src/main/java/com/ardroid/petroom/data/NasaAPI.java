package com.ardroid.petroom.data;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaAPI {

    @GET("planetary/apod")
    Call<GalaxyPicture> getGalaxyPicture(@Query("api_key") String apiKey,@Query("date") String date);

    //https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY
}
