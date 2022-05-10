package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.time.LocalDate;

public class Lease {
    private String firstName;
    private String lastName;
    private int leaseID;
    private Car car;
    private User userID;
    private int leasePeriodInDays;
    private LocalDate startDate;
    private LocalDate endDate;

    public Lease(String firstName, String lastName, int leaseID, Car car, User userID, int leasePeriodInDays, LocalDate startDate, LocalDate endDate) {
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
}
