package com.itcraftsolution.romanager.Retrofit;

import com.itcraftsolution.romanager.Models.CustomerResponse;
import com.itcraftsolution.romanager.Models.CustomerTransactionResponse;
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

    @FormUrlEncoded
    @POST("add_customer.php")
    Call<ResponseModel> addCustomerDetails(@Field("plant_id") String plant_id, @Field("cust_name") String cust_name, @Field("cust_phone") String cust_phone,
                                    @Field("cust_address") String cust_address);


    @GET("get_customers.php")
    Call<CustomerResponse> getAllCustomers();

    @FormUrlEncoded
    @POST("add_customer_given_transaction.php")
    Call<ResponseModel> addCustomerGivenTransaction(@Field("cust_id") int cust_id, @Field("plant_id") int plant_id,@Field("debit") int debit,
                                                               @Field("total") int total,@Field("jag") int jag, @Field("bottle") int bottle, @Field("note") String note, @Field("cust_tra_date") String cust_tra_date);

    @FormUrlEncoded
    @POST("get_plant_details.php")
    Call<PlantResponse> getAllPlantDetails(@Field("auth_id") String auth_id);

    @FormUrlEncoded
    @POST("get_customer_transaction.php")
    Call<CustomerTransactionResponse> getCustomerTransactionDetails(@Field("cust_id") int cust_id, @Field("plant_id") int plant_id);

}
