package com.itcraftsolution.romanager.Models;

public class PlantDetailsModel {
    private int plant_id, plant_status, jag_price, bottle_price;
    private String authId, plantName, plantPhone, plantEmail, plantImage, plantCity, plantAddress, plantSecurity;

    public PlantDetailsModel(int plant_id, String authId, String plantName, String plantPhone, String plantEmail, String plantImage, String plantCity, String plantAddress, String plantSecurity,  int plant_status) {
        this.plant_id = plant_id;
        this.plant_status = plant_status;
        this.authId = authId;
        this.plantName = plantName;
        this.plantPhone = plantPhone;
        this.plantEmail = plantEmail;
        this.plantImage = plantImage;
        this.plantCity = plantCity;
        this.plantAddress = plantAddress;
        this.plantSecurity = plantSecurity;
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

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantPhone() {
        return plantPhone;
    }

    public void setPlantPhone(String plantPhone) {
        this.plantPhone = plantPhone;
    }

    public String getPlantEmail() {
        return plantEmail;
    }

    public void setPlantEmail(String plantEmail) {
        this.plantEmail = plantEmail;
    }

    public String getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(String plantImage) {
        this.plantImage = plantImage;
    }

    public String getPlantCity() {
        return plantCity;
    }

    public void setPlantCity(String plantCity) {
        this.plantCity = plantCity;
    }

    public String getPlantAddress() {
        return plantAddress;
    }

    public void setPlantAddress(String plantAddress) {
        this.plantAddress = plantAddress;
    }

    public String getPlantSecurity() {
        return plantSecurity;
    }

    public void setPlantSecurity(String plantSecurity) {
        this.plantSecurity = plantSecurity;
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
}
