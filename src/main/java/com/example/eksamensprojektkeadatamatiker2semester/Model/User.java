package com.example.eksamensprojektkeadatamatiker2semester.Model;

/* Lavet Af Mohammed */
public class User {

  private int userID;
  private String password;
  private String username;
  private int type;
  private int status;

  public User(int userID, String username, String password, int type, int status) {
    this.userID = userID;
    this.password = password;
    this.username = username;
    this.type = type;
    this.status = status;
  }


  public User(int userID, String username, String password, int type) {
    this.userID = userID;
    this.password = password;
    this.username = username;
    this.type = type;
  }

  public User(String password, String username, int type, int status) {
    this.password = password;
    this.username = username;
    this.type = type;
    this.status = status;
  }

  public User(String password, String username, int type) {
    this.password = password;
    this.username = username;
    this.type = type;
  }

  public User() {
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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  //Lavet en toString for at tjekke om user bliver created, eller om hvilket user der er logget ind
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
