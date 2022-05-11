package com.example.eksamensprojektkeadatamatiker2semester.Model;



public class User {

  private int userID;
  private String password;
  private String username;
  private int type;

  public User(int userID, String password, String username, int type) {
    this.userID = userID;
    this.password = password;
    this.username = username;
    this.type = type;
  }

  public User(String password, String username, int type) {
    this.password = password;
    this.username = username;
    this.type = type;
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

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "User{" +
        "userID=" + userID +
        ", password='" + password + '\'' +
        ", username='" + username + '\'' +
        ", type=" + type +
        '}';
  }
}
