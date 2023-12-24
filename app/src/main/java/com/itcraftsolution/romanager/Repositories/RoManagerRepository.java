package com.itcraftsolution.romanager.Repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itcraftsolution.romanager.Models.PlantDetailsModel;
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
        RequestBody authIdBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getAuthId());
        RequestBody plantNameBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantName());
        RequestBody plantPhoneBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantPhone());
        RequestBody plantEmailBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantEmail());
        RequestBody plantCityBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantCity());
        RequestBody plantAddressBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantAddress());
        RequestBody plantSecurityBody = RequestBody.create(MediaType.parse("text/plain"), plantDetailsModel.getPlantSecurity());

        File plantImageFile = new File(plantDetailsModel.getPlantImage());
        MultipartBody.Part plantImagePart = MultipartBody.Part.createFormData("plant_image", plantImageFile.getName(),
                RequestBody.create(MediaType.parse("image/*"), plantImageFile));

        RetrofitInstance.apiInterface().insertPlantDetails(authIdBody, plantNameBody, plantPhoneBody, plantEmailBody, plantImagePart, plantCityBody, plantAddressBody, plantSecurityBody).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (responseModel != null) {
                    mutableLiveData.setValue(true);
                }else{
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                mutableLiveData.setValue(false);
            }
        });
        return mutableLiveData;
    }
}
