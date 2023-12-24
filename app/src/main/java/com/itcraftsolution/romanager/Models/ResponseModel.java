package com.itcraftsolution.romanager.Models;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    public ResponseModel() {
    }

    public ResponseModel(String status, String message) {
        this.status = status;
        this.message = message;
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
}
