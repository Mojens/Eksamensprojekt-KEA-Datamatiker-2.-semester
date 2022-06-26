package com.example.eksamensprojektkeadatamatiker2semester.Model;
import java.beans.Transient;

/* Lavet Af Malthe */
public class SpecificDamage {
  private int damageID;
  private double price;
  private String description;
  private String picture;
  private String title;
  private int damageReportID;


  private double sumTotal;



  public SpecificDamage(int damageID, double price, String description, String picture, String title, int damageReportID) {
    this.damageID = damageID;
    this.price = price;
    this.description = description;
    this.picture = picture;
    this.title = title;
    this.damageReportID = damageReportID;
  }

  public SpecificDamage(double sumTotal) {
    this.sumTotal = sumTotal;
  }

  public SpecificDamage() {
  }

  public SpecificDamage(double price, String description, String picture, String title, int damageReportID) {
    this.price = price;
    this.description = description;
    this.picture = picture;
    this.title = title;
    this.damageReportID = damageReportID;

  }

  public double getSumTotal() {
    return sumTotal;
  }

  public void setSumTotal(double sumTotal) {
    this.sumTotal = sumTotal;
  }

  // Metoden bliver brugt når et billed skal vises på en html side(/skader).
  // Hvis der ikke er nogen fil gemt i databasen bliver default.png vist
  // Hvis der er en fil gemt i databasen, bliver billed vist.
  // Denne metode bliver kun brugt på html siden skader når man skal vise et billed, nedenunder vises hvordan den bliver brugt.
  // <img th:src="@{${'../'+damage.photosImagePath}}" alt="billed"/>
  @Transient
  public String getPhotosImagePath() {
    if (picture == null || picture.isEmpty()) return "user-photos/" + "default.png";

    return "user-photos" + "/" + picture;
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
