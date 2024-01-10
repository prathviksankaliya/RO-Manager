package com.itcraftsolution.romanager.Models;

public class CustomerTransactionModel {
    private int cust_tra_id, cust_id, plant_id, debit, credit, total, jag, bottle;
    private String note, cust_tra_date;

    public CustomerTransactionModel(int cust_tra_id, int cust_id, int plant_id,int debit, int credit, int total, int jag, int bottle, String note, String cust_tra_date) {
        this.cust_tra_id = cust_tra_id;
        this.cust_id = cust_id;
        this.plant_id = plant_id;
        this.debit = debit;
        this.credit = credit;
        this.total = total;
        this.jag = jag;
        this.bottle = bottle;
        this.note = note;
        this.cust_tra_date = cust_tra_date;
    }

    public CustomerTransactionModel() {
    }

    public CustomerTransactionModel(int cust_id, int plant_id,int debit, int total,int jag, int bottle, String note, String cust_tra_date) {
        this.cust_id = cust_id;
        this.plant_id = plant_id;
        this.debit = debit;
        this.total = total;
        this.jag = jag;
        this.bottle = bottle;
        this.note = note;
        this.cust_tra_date = cust_tra_date;
    }

    public int getCust_tra_id() {
        return cust_tra_id;
    }

    public void setCust_tra_id(int cust_tra_id) {
        this.cust_tra_id = cust_tra_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getJag() {
        return jag;
    }

    public void setJag(int jag) {
        this.jag = jag;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCust_tra_date() {
        return cust_tra_date;
    }

    public void setCust_tra_date(String cust_tra_date) {
        this.cust_tra_date = cust_tra_date;
    }
}
