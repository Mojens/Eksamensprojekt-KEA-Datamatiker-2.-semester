package com.example.eksamensprojektkeadatamatiker2semester.Model;

/* Lavet Af Simon */
public class Car {
  private int vognNummer;
  private String stelNummer;
  private String brand;
  private String model;
  private double price;
  private int isLeased;


  public Car(int vognNummer, String stelNummer, String brand, String model, double price, int isLeased) {
    this.vognNummer = vognNummer;
    this.stelNummer = stelNummer;
    this.brand = brand;
    this.model = model;
    this.price = price;
    this.isLeased = isLeased;

  }

  public Car(int vognNummer, String brand, String model, double price, int isLeased) {
    this.vognNummer = vognNummer;
    this.brand = brand;
    this.model = model;
    this.price = price;
    this.isLeased = isLeased;
  }
  public Car(String stelNummer, String brand, String model, double price, int isLeased) {
    this.stelNummer = stelNummer;
    this.brand = brand;
    this.model = model;
    this.price = price;
    this.isLeased = isLeased;

  }
  public Car(String brand, String model) {
    this.brand = brand;
    this.model = model;

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

  public int isLeased() {
    return isLeased;
  }

  public void setLeased(int leased) {
    isLeased = leased;
  }

  @Override
  public String toString() {
    return vognNummer +
        ", " + brand +
        ", " + model;
  }
}
