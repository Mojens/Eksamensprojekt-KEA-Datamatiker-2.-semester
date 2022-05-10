package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.time.LocalDate;

public class Lease {
    private String firstName;
    private String lastName;
    private int leaseID;
    private Car car;
    private User userID;
    private int leasePeriodInDays;
    private int startDate;
    private int endDate;

    public Lease(String firstName, String lastName, int leaseID, Car car, User userID, int leasePeriodInDays, int startDate, int endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaseID = leaseID;
        this.car = car;
        this.userID = userID;
        this.leasePeriodInDays = leasePeriodInDays;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
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
