package com.example.eksamensprojektkeadatamatiker2semester.Model;



public class User {

  private int userID;
  private String password;
  private String username;

  public User(int userID, String password, String username) {
    this.userID = userID;
    this.password = password;
    this.username = username;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
