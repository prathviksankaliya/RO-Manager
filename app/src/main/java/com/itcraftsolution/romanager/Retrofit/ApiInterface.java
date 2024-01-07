package com.itcraftsolution.romanager.Retrofit;

import com.itcraftsolution.romanager.Models.CustomerResponse;
import com.itcraftsolution.romanager.Models.PlantResponse;
import com.itcraftsolution.romanager.Models.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("insert_plant_details.php")
    Call<ResponseModel> insertPlantDetails(@Part("auth_id") RequestBody auth_id, @Part("plant_name") RequestBody plant_name,
                                           @Part("plant_phone") RequestBody plant_phone, @Part("plant_email") RequestBody plant_email,
                                           @Part MultipartBody.Part plant_image, @Part("plant_city") RequestBody plant_city,
                                           @Part("plant_address") RequestBody plant_address, @Part("plant_security") RequestBody plant_security);

    @Multipart
    @POST("add_customer.php")
    Call<ResponseModel> addCustomerDetails(@Part("cust_name") RequestBody cust_name, @Part("cust_phone") RequestBody cust_phone,
                                    @Part("cust_address") RequestBody cust_address);


    @GET("get_customers.php")
    Call<CustomerResponse> getAllCustomers();

    @POST("add_customer_given_transaction.php")
    Call<ResponseModel> addCustomerGivenTransaction(@Field("cust_id") int cust_id, @Field("debit") int debit,
                                                               @Field("jag") int jag, @Field("bottle") int bottle, @Field("note") String note, @Field("cust_tra_date") String cust_tra_date);

    @FormUrlEncoded
    @POST("get_plant_details.php")
    Call<PlantResponse> getAllPlantDetails(@Field("auth_id") String auth_id);

}
