package com.itcraftsolution.romanager.Models;

public class CustomerModel {

    private int cust_id, status, cust_tra_id, debit, credit, total_balance;
    private String cust_name, cust_phone, cust_address, cust_image,money_status, cust_msg, cust_Date, cust_tra_date;

    public CustomerModel(int cust_id, int status, int cust_tra_id, int debit, int credit, int total_balance, String cust_name, String cust_phone, String cust_address, String cust_image, String money_status, String cust_msg, String cust_Date, String cust_tra_date) {
        this.cust_id = cust_id;
        this.status = status;
        this.cust_tra_id = cust_tra_id;
        this.debit = debit;
        this.credit = credit;
        this.total_balance = total_balance;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.cust_address = cust_address;
        this.cust_image = cust_image;
        this.money_status = money_status;
        this.cust_msg = cust_msg;
        this.cust_Date = cust_Date;
        this.cust_tra_date = cust_tra_date;
    }

    public CustomerModel() {
    }

    public CustomerModel(int cust_id, int status, String cust_name, String cust_phone, String cust_address, String cust_image, String cust_Date) {
        this.cust_id = cust_id;
        this.status = status;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.cust_address = cust_address;
        this.cust_image = cust_image;
        this.cust_Date = cust_Date;
    }

    public CustomerModel(int cust_id, int cust_tra_id, int debit, int credit, int total_balance, String money_status, String cust_msg, String cust_tra_date) {
        this.cust_id = cust_id;
        this.cust_tra_id = cust_tra_id;
        this.debit = debit;
        this.credit = credit;
        this.total_balance = total_balance;
        this.money_status = money_status;
        this.cust_msg = cust_msg;
        this.cust_tra_date = cust_tra_date;
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

    public int getCust_tra_id() {
        return cust_tra_id;
    }

    public void setCust_tra_id(int cust_tra_id) {
        this.cust_tra_id = cust_tra_id;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(int total_balance) {
        this.total_balance = total_balance;
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

    public String getCust_image() {
        return cust_image;
    }

    public void setCust_image(String cust_image) {
        this.cust_image = cust_image;
    }

    public String getMoney_status() {
        return money_status;
    }

    public void setMoney_status(String money_status) {
        this.money_status = money_status;
    }

    public String getCust_msg() {
        return cust_msg;
    }

    public void setCust_msg(String cust_msg) {
        this.cust_msg = cust_msg;
    }

    public String getCust_Date() {
        return cust_Date;
    }

    public void setCust_Date(String cust_Date) {
        this.cust_Date = cust_Date;
    }

    public String getCust_tra_date() {
        return cust_tra_date;
    }

    public void setCust_tra_date(String cust_tra_date) {
        this.cust_tra_date = cust_tra_date;
    }
}
