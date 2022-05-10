package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class SpecificDamage {
  private int price;
  private String description;
  private String picture;
  private int damageID;
  private String title;

  public SpecificDamage(int price, String description, String picture, int damageID, String title) {
    this.price = price;
    this.description = description;
    this.picture = picture;
    this.damageID = damageID;
    this.title = title;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
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
