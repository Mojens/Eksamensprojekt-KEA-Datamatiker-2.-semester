package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.util.Date;
import java.time.LocalDate;

public class Lease {
  private int leaseID;
  private String firstName;
  private String lastName;
  private int leasePeriodInDays;
  private int userID;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate today = LocalDate.now();


  public Lease(int leaseID, String firstName, String lastName, int leasePeriodInDays, int userID, LocalDate startDate, LocalDate endDate) {
    this.leaseID = leaseID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.leasePeriodInDays = leasePeriodInDays;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Lease(String firstName, String lastName, int leasePeriodInDays, int userID, LocalDate startDate, LocalDate endDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.leasePeriodInDays = leasePeriodInDays;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Lease() {
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

  public int getLeaseID() {
    return leaseID;
  }

  public void setLeaseID(int leaseID) {
    this.leaseID = leaseID;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getLeasePeriodInDays() {
    return leasePeriodInDays;
  }

  public void setLeasePeriodInDays(int leasePeriodInDays) {
    this.leasePeriodInDays = leasePeriodInDays;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LocalDate compareNowAndEndDate(){
    int compareValue = today.compareTo(endDate);

    if (compareValue > 0) {
      System.out.println("today is latter than 4th-Jan-2022");
    } else if (compareValue < 0) {
      System.out.println("today is earlier than 4th-Jan-2022");
    } else {
      System.out.println("both dates are equal");
    }
    return endDate;
  }



}
