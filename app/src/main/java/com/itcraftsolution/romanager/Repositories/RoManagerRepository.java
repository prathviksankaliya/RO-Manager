package com.itcraftsolution.romanager.Repositories;

import android.content.Context;
import android.widget.Toast;

import com.itcraftsolution.romanager.Models.PlantDetailsModel;
import com.itcraftsolution.romanager.Models.ResponseModel;
import com.itcraftsolution.romanager.Retrofit.ApiInterface;
import com.itcraftsolution.romanager.Retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoManagerRepository {

    private Context context;
    private ResponseModel model;

    public RoManagerRepository(Context context) {
        this.context = context;
    }

    public ResponseModel insertPlantDetails(PlantDetailsModel plantDetailsModel){
        RetrofitInstance.apiInterface().insertPlantDetails(plantDetailsModel.getAuthId(), plantDetailsModel.getPlantName(), plantDetailsModel.getPlantPhone(), plantDetailsModel.getPlantEmail(), plantDetailsModel.getPlantImage(), plantDetailsModel.getPlantCity(), plantDetailsModel.getPlantAddress(), plantDetailsModel.getPlantSecurity()).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.body() != null && response.isSuccessful()){
                    model = response.body();
                }
                else {
                    Toast.makeText(context, "Something went Wrong!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return model;
    }
}
