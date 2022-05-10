package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.util.ArrayList;

public class DamageReport {
    private ArrayList<SpecificDamage> specificDamage;
    private User userID;
    private Lease leaseID;

    public DamageReport(ArrayList<SpecificDamage> specificDamage, User userID, Lease leaseID) {
        this.specificDamage = specificDamage;
        this.userID = userID;
        this.leaseID = leaseID;
    }

    public DamageReport() {
    }

    public ArrayList<SpecificDamage> getSpecificDamage() {
        return specificDamage;
    }

    public void setSpecificDamage(ArrayList<SpecificDamage> specificDamage) {
        this.specificDamage = specificDamage;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Lease getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(Lease leaseID) {
        this.leaseID = leaseID;
    }
}
