package com.example.eksamensprojektkeadatamatiker2semester.Model;
/* Lavet Af Malthe */
public class DamageReport {
  private int damageReportID;
  private int leaseID;
  private int vognNummer;
  private int employeeID;
  private int status;


  public DamageReport(int damageReportID, int leaseID, int vognNummer, int employeeID) {
    this.damageReportID = damageReportID;
    this.leaseID = leaseID;
    this.vognNummer = vognNummer;
    this.employeeID = employeeID;
  }

  public DamageReport(int leaseID, int vognNummer, int employeeID) {
    this.leaseID = leaseID;
    this.vognNummer = vognNummer;
    this.employeeID = employeeID;
  }

  public DamageReport(int damageReportID, int leaseID, int vognNummer, int employeeID, int status) {
    this.damageReportID = damageReportID;
    this.leaseID = leaseID;
    this.vognNummer = vognNummer;
    this.employeeID = employeeID;
    this.status = status;
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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
