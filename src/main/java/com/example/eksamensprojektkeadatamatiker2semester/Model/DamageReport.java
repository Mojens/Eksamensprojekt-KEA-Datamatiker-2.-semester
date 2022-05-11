package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class DamageReport {
    private int damageReportID;

    private int employeeID;
    private int leaseID;
    private int vognNummer;


    public DamageReport(int damageReportID, int userID, int leaseID, int vognNummer) {
        this.damageReportID = damageReportID;
        this.employeeID = userID;
        this.leaseID = leaseID;
        this.vognNummer = vognNummer;
    }

    public int getVognNummer() {
        return vognNummer;
    }

    public void setVognNummer(int vognNummer) {
        this.vognNummer = vognNummer;
    }

    public DamageReport() {
    }

    public int getDamageReportID() {
        return damageReportID;
    }

    public void setDamageReportID(int damageReportID) {
        this.damageReportID = damageReportID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }
}
