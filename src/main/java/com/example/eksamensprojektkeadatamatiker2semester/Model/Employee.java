package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class Employee {

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private int employeeID;
  private String eMail;
  private int userID;

  private int status;

  public Employee(String firstName, String lastName, String phoneNumber, int employeeID, String eMail, int userID) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.employeeID = employeeID;
    this.eMail = eMail;
    this.userID = userID;
  }

  public Employee(String firstName, String lastName, String phoneNumber, int employeeID, String eMail, int userID, int status) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.employeeID = employeeID;
    this.eMail = eMail;
    this.userID = userID;
    this.status = status;
  }

  public Employee(String firstName, String lastName, String phoneNumber, String eMail, int userID) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.eMail = eMail;
    this.userID = userID;
  }

  public Employee(String firstName, String lastName, String phoneNumber, String eMail, int userID, int status) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.eMail = eMail;
    this.userID = userID;
    this.status = status;
  }

  public Employee() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID = employeeID;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }

}
