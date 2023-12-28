package com.itcraftsolution.romanager.Models;

public class CustomerModel {
    private int cust_id, status;
    private String cust_name, cust_phone, cust_address, cust_date, cust_msg;

    public CustomerModel(int cust_id, int status, String cust_name, String cust_phone, String cust_address, String cust_date, String cust_msg) {
        this.cust_id = cust_id;
        this.status = status;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.cust_address = cust_address;
        this.cust_date = cust_date;
        this.cust_msg = cust_msg;
    }

    public CustomerModel() {
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_date() {
        return cust_date;
    }

    public void setCust_date(String cust_date) {
        this.cust_date = cust_date;
    }

    public String getCust_msg() {
        return cust_msg;
    }

    public void setCust_msg(String cust_msg) {
        this.cust_msg = cust_msg;
    }
}
