package com.example.eksamensprojektkeadatamatiker2semester.Model;



public class User {

  private String userID;
  private String password;
  private String username;

  public User(String userID, String password, String username) {
    this.userID = userID;
    this.password = password;
    this.username = username;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
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
