package com.itcraftsolution.romanager.Models;

public class PlantDetailsModel {
    private int plant_id, plant_status, jag_price, bottle_price;
    private String auth_id, plant_name, plant_phone, plant_email, plant_image, plant_city, plant_address, plant_security;

    public PlantDetailsModel(int plant_id, int plant_status, int jag_price, int bottle_price, String auth_id, String plant_name, String plant_phone, String plant_email, String plant_image, String plant_city, String plant_address, String plant_security) {
        this.plant_id = plant_id;
        this.plant_status = plant_status;
        this.jag_price = jag_price;
        this.bottle_price = bottle_price;
        this.auth_id = auth_id;
        this.plant_name = plant_name;
        this.plant_phone = plant_phone;
        this.plant_email = plant_email;
        this.plant_image = plant_image;
        this.plant_city = plant_city;
        this.plant_address = plant_address;
        this.plant_security = plant_security;
    }

    public PlantDetailsModel() {
    }

    public PlantDetailsModel(int jag_price, int bottle_price, String auth_id, String plant_name, String plant_phone, String plant_email, String plant_image, String plant_city, String plant_address, String plant_security) {
        this.jag_price = jag_price;
        this.bottle_price = bottle_price;
        this.auth_id = auth_id;
        this.plant_name = plant_name;
        this.plant_phone = plant_phone;
        this.plant_email = plant_email;
        this.plant_image = plant_image;
        this.plant_city = plant_city;
        this.plant_address = plant_address;
        this.plant_security = plant_security;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public int getPlant_status() {
        return plant_status;
    }

    public void setPlant_status(int plant_status) {
        this.plant_status = plant_status;
    }

    public int getJag_price() {
        return jag_price;
    }

    public void setJag_price(int jag_price) {
        this.jag_price = jag_price;
    }

    public int getBottle_price() {
        return bottle_price;
    }

    public void setBottle_price(int bottle_price) {
        this.bottle_price = bottle_price;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getPlant_phone() {
        return plant_phone;
    }

    public void setPlant_phone(String plant_phone) {
        this.plant_phone = plant_phone;
    }

    public String getPlant_email() {
        return plant_email;
    }

    public void setPlant_email(String plant_email) {
        this.plant_email = plant_email;
    }

    public String getPlant_image() {
        return plant_image;
    }

    public void setPlant_image(String plant_image) {
        this.plant_image = plant_image;
    }

    public String getPlant_city() {
        return plant_city;
    }

    public void setPlant_city(String plant_city) {
        this.plant_city = plant_city;
    }

    public String getPlant_address() {
        return plant_address;
    }

    public void setPlant_address(String plant_address) {
        this.plant_address = plant_address;
    }

    public String getPlant_security() {
        return plant_security;
    }

    public void setPlant_security(String plant_security) {
        this.plant_security = plant_security;
    }
}
