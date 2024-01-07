package com.itcraftsolution.romanager.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private PlantDetailsModel plantDetails;

    public PlantResponse() {
    }

    public PlantResponse(String status, String message, PlantDetailsModel plantDetails) {
        this.status = status;
        this.message = message;
        this.plantDetails = plantDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlantDetailsModel getPlantDetails() {
        return plantDetails;
    }

    public void setPlantDetails(PlantDetailsModel plantDetails) {
        this.plantDetails = plantDetails;
    }
}


