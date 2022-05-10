package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class Car {

    private String stelNummer;
    private int vognNummer;
    private int price;
    private String brand;
    private String model;
    private boolean isLeased;

    public Car(String stelNummer, int vognNummer, int price, String brand, String model, boolean isLeased) {
        this.stelNummer = stelNummer;
        this.vognNummer = vognNummer;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.isLeased = isLeased;
    }

    public Car() {

    }

    public String getStelNummer() {
        return stelNummer;
    }

    public void setStelNummer(String stelNummer) {
        this.stelNummer = stelNummer;
    }

    public int getVognNummer() {
        return vognNummer;
    }

    public void setVognNummer(int vognNummer) {
        this.vognNummer = vognNummer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isLeased() {
        return isLeased;
    }

    public void setLeased(boolean leased) {
        isLeased = leased;
    }
}
