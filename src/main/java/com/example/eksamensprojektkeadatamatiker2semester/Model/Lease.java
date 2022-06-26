package com.example.eksamensprojektkeadatamatiker2semester.Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/* Lavet Af Malthe og Mohammed */
public class Lease {
  private int leaseID;
  private String firstName;
  private String lastName;
  private int userID;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate today = LocalDate.now();
  private int status;

  public Lease(int leaseID, String firstName, String lastName, int userID, LocalDate startDate, LocalDate endDate) {
    this.leaseID = leaseID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
  }
  public Lease(int leaseID, String firstName, String lastName, int userID, LocalDate startDate, LocalDate endDate, int status) {
    this.leaseID = leaseID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public Lease(String firstName, String lastName, int userID, LocalDate startDate, LocalDate endDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
  }

    public Lease(String firstName, String lastName, int userID, LocalDate startDate, LocalDate endDate,int status) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  //Metoden sammenligner dagens dato og endDate. Den retunere henholdsvis 1 hvis lejeperioden er overstået
  // eller lejeperiodens overståelse er i dag. Den retunere 0 hvis lejeperioden ikke er overstået.
  public int compareNowAndEndDate(LocalDate date){
    int compareValue = today.compareTo(date);

    if (compareValue > 0) {
      return 1;
    } else if (compareValue < 0) {
      return 0;
    } else {
      System.out.println("both dates are equal");
      return 1;
    }
  }

  //Metoden har 2 parameter, henholdvist startDate og endDate. Den regner forskellen ud mellem endDate og startDate.
  // Altså Dage i mellem disse 2 datoer.
  // Den bliver brugt til at udregne hvor mange dage en lejeaftale er i alt.
  public long subtractDates(LocalDate startDate, LocalDate endDate){
    return startDate.until(endDate, ChronoUnit.DAYS);
  }

  @Override
  public String toString() {
    return "Lease{" +
        "leaseID=" + leaseID +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", userID=" + userID +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", today=" + today +
        ", status=" + status +
        '}';
  }
}
