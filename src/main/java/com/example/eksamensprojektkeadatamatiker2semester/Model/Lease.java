package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.time.LocalDate;

public class Lease {
    private int leaseID;
    private String firstName;
    private String lastName;
    private int leasePeriodInDays;
    private int userID;
    private int startDate;
    private int endDate;


    public Lease(int leaseID, String firstName, String lastName, int leasePeriodInDays, int userID, int startDate, int endDate) {
        this.leaseID = leaseID;
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

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
}
