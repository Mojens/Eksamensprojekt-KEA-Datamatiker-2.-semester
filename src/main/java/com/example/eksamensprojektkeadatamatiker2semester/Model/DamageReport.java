package com.example.eksamensprojektkeadatamatiker2semester.Model;

import java.util.ArrayList;

public class DamageReport {
    private int damageReportID;
    private int userID;
    private int leaseID;


    public DamageReport(int damageReportID, int userID, int leaseID) {
        this.damageReportID = damageReportID;
        this.userID = userID;
        this.leaseID = leaseID;
    }

    public DamageReport() {
    }

    public int getDamageReportID() {
        return damageReportID;
    }

    public void setDamageReportID(int damageReportID) {
        this.damageReportID = damageReportID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }
}
