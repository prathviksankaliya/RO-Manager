package com.itcraftsolution.romanager.Repositories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itcraftsolution.romanager.Models.CustomerModel;
import com.itcraftsolution.romanager.Models.CustomerResponse;
import com.itcraftsolution.romanager.Models.CustomerTransactionModel;
import com.itcraftsolution.romanager.Models.PlantDetailsModel;
import com.itcraftsolution.romanager.Models.PlantResponse;
import com.itcraftsolution.romanager.Models.ResponseModel;
import com.itcraftsolution.romanager.Retrofit.RetrofitInstance;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoManagerRepository {

    private Context context;

    public RoManagerRepository(Context context) {
        this.context = context;
    }

    public LiveData<Boolean> insertPlantDetails(PlantDetailsModel plantDetailsModel) {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        RequestBody authIdBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getAuth_id());
        RequestBody plantNameBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_name());
        RequestBody plantPhoneBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_phone());
        RequestBody plantEmailBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_email());
        RequestBody plantCityBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_city());
        RequestBody plantAddressBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_address());
        RequestBody plantSecurityBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlant_security());

        File plantImageFile = new File(plantDetailsModel.getPlant_image());
        MultipartBody.Part plantImagePart = MultipartBody.Part.createFormData("plant_image", plantImageFile.getName(),
                RequestBody.create(MediaType.parse("image/*"), plantImageFile));

        RetrofitInstance.apiInterface().insertPlantDetails(authIdBody, plantNameBody, plantPhoneBody, plantEmailBody, plantImagePart, plantCityBody, plantAddressBody, plantSecurityBody).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (responseModel != null) {
                    mutableLiveData.setValue(true);
                } else {
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                mutableLiveData.setValue(false);
                Log.d("insertPlantDetailsLog", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<Boolean> addCustomerDetails(CustomerModel model) {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        RequestBody cust_name = RequestBody.create(MediaType.parse("text/plain"), model.getCust_name());
        RequestBody cust_phone = RequestBody.create(MediaType.parse("text/plain"), model.getCust_phone());
        RequestBody cust_address = RequestBody.create(MediaType.parse("text/plain"), model.getCust_address());

        RetrofitInstance.apiInterface().addCustomerDetails(cust_name, cust_phone, cust_address).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (responseModel != null) {
                    mutableLiveData.setValue(true);
                } else {
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                mutableLiveData.setValue(false);
                Log.d("Customer_Api_Log", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<PlantResponse> getAllPlantDetails(String auth_id) {
        MutableLiveData<PlantResponse> liveData = new MutableLiveData<>();

        RetrofitInstance.apiInterface().getAllPlantDetails(auth_id).enqueue(new Callback<PlantResponse>() {
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                PlantResponse plantResponse = response.body();
                if (plantResponse != null) {
                    if (plantResponse.getStatus().equals("success")) {
                        liveData.setValue(plantResponse);
                    }else{
                        Toast.makeText(context, plantResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlantResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return liveData;
    }

    public LiveData<CustomerResponse> getAllCustomers() {
        MutableLiveData<CustomerResponse> liveData = new MutableLiveData<>();
        RetrofitInstance.apiInterface().getAllCustomers().enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                CustomerResponse customerResponse = response.body();
                if (customerResponse != null) {
                    if (customerResponse.getStatus().equals("success")) {
                        liveData.setValue(customerResponse);
                    }else{
                        Toast.makeText(context, customerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Rv_Customer", "Repo : " + customerResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Rv_Customer", "repo fail : " +t.getMessage());
            }
        });
        return liveData;
    }

    public LiveData<Boolean> addCustomerGivenTransaction(CustomerTransactionModel model){
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        RetrofitInstance.apiInterface().addCustomerGivenTransaction(model.getCust_id(), model.getDebit(), model.getJag(), model.getBottle(), model.getNote(), model.getCust_tra_date()).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (responseModel != null) {
                    liveData.setValue(true);
                } else {
                    liveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                liveData.setValue(false);
            }
        });
        return liveData;
     }
}
