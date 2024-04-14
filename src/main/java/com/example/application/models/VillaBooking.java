package com.example.application.models;

public class VillaBooking {
    private String id;
    private String username;
    private String villaName;
    private String address;
    private String totalPrice;
    private String dateBooked;
    private String paid;

    public VillaBooking(String id, String username, String villaName, String address, String totalPrice, String dateBooked, String paid) {
        this.id = id;
        this.username = username;
        this.villaName = villaName;
        this.address = address;
        this.totalPrice = totalPrice;
        this.dateBooked = dateBooked;
        this.paid = paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVillaName() {
        return villaName;
    }

    public void setVillaName(String villaName) {
        this.villaName = villaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
