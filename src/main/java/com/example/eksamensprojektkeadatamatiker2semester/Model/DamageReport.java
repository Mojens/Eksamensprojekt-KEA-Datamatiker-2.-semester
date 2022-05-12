package com.example.eksamensprojektkeadatamatiker2semester.Model;

public class DamageReport {
    private int damageReportID;
    private int leaseID;
    private int vognNummer;
    private int employeeID;


    public DamageReport(int damageReportID, int leaseID, int vognNummer, int employeeID) {
        this.damageReportID = damageReportID;
        this.leaseID = leaseID;
        this.vognNummer = vognNummer;
        this.employeeID = employeeID;
    }

    public DamageReport() {
    }

    public int getVognNummer() {
        return vognNummer;
    }

    public void setVognNummer(int vognNummer) {
        this.vognNummer = vognNummer;
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
