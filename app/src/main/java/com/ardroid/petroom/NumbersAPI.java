package com.ardroid.petroom;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface NumbersAPI {

    @GET("{number}")
    Call<ResponseBody> getFact(@Path("number") int number);

    @GET ("http://numbersapi.com/random/trivia")
    Call<ResponseBody> getData();
}
