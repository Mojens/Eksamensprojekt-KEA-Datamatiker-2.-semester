package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.beans.Transient;

public class SpecificDamage {
  private double price;
  private String description;
  private String picture;
  private int damageID;
  private String title;
  private int leaseID;
  private double sumTotal;
  private int damageReportID;


  public SpecificDamage(int damageReportID,double price, String description, String picture, String title, int damageID, int leaseID) {
    this.price = price;
    this.description = description;
    this.picture = picture;
    this.damageID = damageID;
    this.title = title;
    this.leaseID = leaseID;
    this.damageReportID = damageReportID;
  }

  public SpecificDamage(double sumTotal){
    this.sumTotal = sumTotal;
  }

  public SpecificDamage() {
  }


  public double getSumTotal() {
    return sumTotal;
  }

  public void setSumTotal(double sumTotal) {
    this.sumTotal = sumTotal;
  }

  @Transient
  public String getPhotosImagePath() {
    if (picture == null || picture.isEmpty()) return "user-photos/" + "default.jpg";

    return "user-photos" + "/" + picture;
  }

  public int getLeaseID() {
    return leaseID;
  }

  public void setLeaseID(int leaseID) {
    this.leaseID = leaseID;
  }

  public int getDamageReportID() {
    return damageReportID;
  }

  public void setDamageReportID(int damageReportID) {
    this.damageReportID = damageReportID;
  }

  public double getPrice() {
    return price;
  }



  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public int getDamageID() {
    return damageID;
  }

  public void setDamageID(int damageID) {
    this.damageID = damageID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
