package com.itcraftsolution.romanager.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<CustomerModel> customerDetails;

    public CustomerResponse() {
    }

    public CustomerResponse(String status, String message, List<CustomerModel> customerDetails) {
        this.status = status;
        this.message = message;
        this.customerDetails = customerDetails;
    }

    public CustomerResponse(String status, List<CustomerModel> customerDetails) {
        this.status = status;
        this.customerDetails = customerDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerModel> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<CustomerModel> customerDetails) {
        this.customerDetails = customerDetails;
    }
}
