package com.itcraftsolution.romanager.ViewModels;

import android.app.Application;
import android.text.BoringLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.itcraftsolution.romanager.Models.CustomerModel;
import com.itcraftsolution.romanager.Models.CustomerResponse;
import com.itcraftsolution.romanager.Models.PlantDetailsModel;
import com.itcraftsolution.romanager.Repositories.RoManagerRepository;

public class RoManagerViewModel extends AndroidViewModel {
    private RoManagerRepository repository;

    public RoManagerViewModel(@NonNull Application application) {
        super(application);
        repository = new RoManagerRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> insertPlantDetails(PlantDetailsModel model) {
        return repository.insertPlantDetails(model);
    }

    public LiveData<Boolean> addCustomerDetails(CustomerModel model){
        return repository.addCustomerDetails(model);
    }

    public LiveData<CustomerResponse> getAllCustomers(){
        return repository.getAllCustomers();
    }

}
