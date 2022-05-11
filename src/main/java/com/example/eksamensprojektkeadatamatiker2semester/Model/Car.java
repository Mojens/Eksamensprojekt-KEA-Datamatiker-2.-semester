package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class Car {
    private int vognNummer;
    private String stelNummer;
    private String brand;
    private String model;
    private double price;
    private boolean isLeased;

    private int leaseID;

    public Car(int vognNummer, String stelNummer, String brand, String model, double price, boolean isLeased, int leaseID) {
        this.vognNummer = vognNummer;
        this.stelNummer = stelNummer;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.isLeased = isLeased;
        this.leaseID = leaseID;
    }

    public Car() {

    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
