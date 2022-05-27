package com.example.eksamensprojektkeadatamatiker2semester.Repository;
import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/* Lavet Af Malthe */
@Repository
public class DamageReportRepository {
  Connection connection;

  public DamageReportRepository() {
    connection = ConnectionManager.getConnection();
  }

  public List<DamageReport> showAllDamageReports() {
    List<DamageReport> reportList = new ArrayList<>();
    final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int damageReportID = rs.getInt(1);
        int leaseID = rs.getInt(2);
        int vognNummer = rs.getInt(3);
        int employeeID = rs.getInt(4);
        int status = rs.getInt(5);

        reportList.add(new DamageReport(damageReportID, leaseID, vognNummer, employeeID,status));

      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle skader");
      e.printStackTrace();
    }
    return reportList;

  }

  public DamageReport checkIfExists(int lease, int car) {
    DamageReport check = new DamageReport();
    final String SQL_SHOW_REPORT = "SELECT DamageReportID, Leases_leaseID, Cars_vognNummer FROM DamageReport WHERE Leases_leaseID = '" + lease + "'" +
        "AND Cars_vognNummer = '" + car + "'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int damageReportID = rs.getInt(1);
        int leaseID = rs.getInt(2);
        int vognNummer = rs.getInt(3);

        check.setDamageReportID(damageReportID);
        check.setLeaseID(leaseID);
        check.setVognNummer(vognNummer);



      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle skader");
      e.printStackTrace();
    }
    return check;

  }


  public DamageReport findReportByID(int reportID) {
    DamageReport report = new DamageReport();
    final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport WHERE damageReportID = '" + reportID + "'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int damageReportID = rs.getInt(1);
        int leaseID = rs.getInt(2);
        int vognNummer = rs.getInt(3);
        int employeeID = rs.getInt(4);
        int status = rs.getInt(5);
        report.setDamageReportID(damageReportID);
        report.setEmployeeID(employeeID);
        report.setLeaseID(leaseID);
        report.setVognNummer(vognNummer);
        report.setStatus(status);

      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle skader");
      e.printStackTrace();
    }
    return report;
  }

  public List<DamageReport> findReportByLast() {
    List<DamageReport> report = new ArrayList<>();
    final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport ORDER BY damageReportID desc";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int damageReportID = rs.getInt(1);
        int leaseID = rs.getInt(2);
        int vognNummer = rs.getInt(3);
        int employeeID = rs.getInt(4);
        int status = rs.getInt(5);

        report.add(new DamageReport(damageReportID, leaseID, vognNummer, employeeID, status));

      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle skader");
      e.printStackTrace();
    }
    return report;
  }


  public boolean addDamageReport(DamageReport damageReport) {

    final String SQL_ADD_QUERY = "INSERT INTO DamageReport(DamageReportID,Leases_leaseID,Cars_vognNummer,Employee_employeeID) VALUES(?,?,?,?)";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

      ps.setInt(1, damageReport.getDamageReportID());
      ps.setInt(2, damageReport.getLeaseID());
      ps.setInt(3, damageReport.getVognNummer());
      ps.setInt(4, damageReport.getEmployeeID());

      ps.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke tilføje ny skaderapport");
      return false;
    }

  }

  public boolean deleteDamageReport(int reportID) {
    final String SQL_DELETE = "DELETE FROM DamageReport WHERE damageReportID = ?";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

      ps.setInt(1, reportID);

      ps.executeUpdate();

      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke slette skaderapporten");
      e.printStackTrace();
      return false;
    }

  }

  public boolean editDamageReport(DamageReport dr, int id) {
    final String SQL_EDIT = "UPDATE DamageReport SET Employee_EmployeeID = ?, Leases_leaseID = ?,Cars_vognNummer = ? WHERE damageReportID = '"+id+"'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

      ps.setInt(1, dr.getEmployeeID());
      ps.setInt(2, dr.getLeaseID());
      ps.setInt(3, dr.getVognNummer());

      ps.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke opdatere skaderapporten");
      e.printStackTrace();
      return false;
    }

  }

  public void ChangeStatusDamageReportID(int id) {

    final String QUERYDELETE = "UPDATE DamageReport SET DamageReport.status = 0 WHERE damageReportID = "+"'"+id+"'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);
      //Da vi har sat foreign key på update at den skal cascade og ikke restrict
      preparedStatement.executeUpdate();
      preparedStatement.close();

    } catch (SQLException e) {
      System.out.println("kunne ikke opdatere skadesrapporten til klar");
      e.printStackTrace();
    }


  }

}
