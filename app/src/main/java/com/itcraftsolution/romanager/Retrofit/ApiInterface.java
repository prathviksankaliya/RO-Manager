package com.itcraftsolution.romanager.Retrofit;

import com.itcraftsolution.romanager.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("insert_plant_details.php")
    Call<ResponseModel> insertPlantDetails(@Field("auth_id") String auth_id, @Field("plant_name") String plant_name,
                                           @Field("plant_phone") String plant_phone, @Field("plant_email") String plant_email,
                                           @Field("plant_image") String plant_image, @Field("plant_city") String plant_city,
                                           @Field("plant_address") String plant_address, @Field("plant_security") String plant_security);
}
