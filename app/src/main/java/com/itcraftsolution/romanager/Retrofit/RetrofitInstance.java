package com.itcraftsolution.romanager.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String BASE_URL = "http://192.168.0.108/romanager_api/";

    public static Retrofit retrofit = null;

    public static ApiInterface apiInterface(){
            if (retrofit == null) {

//                Gson gson = new GsonBuilder()
//                        .setLenient()
//                        .create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit.create(ApiInterface.class);
        }
}
