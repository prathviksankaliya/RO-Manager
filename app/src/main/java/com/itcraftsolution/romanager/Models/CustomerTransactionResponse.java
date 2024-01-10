package com.itcraftsolution.romanager.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerTransactionResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<CustomerTransactionModel> customerTransactionModels;

    public CustomerTransactionResponse() {
    }

    public CustomerTransactionResponse(String status, String message, List<CustomerTransactionModel> customerTransactionModels) {
        this.status = status;
        this.message = message;
        this.customerTransactionModels = customerTransactionModels;
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

    public List<CustomerTransactionModel> getCustomerTransactionModels() {
        return customerTransactionModels;
    }

    public void setCustomerTransactionModels(List<CustomerTransactionModel> customerTransactionModels) {
        this.customerTransactionModels = customerTransactionModels;
    }
}
