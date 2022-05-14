package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DamageReportRepository {
    Connection connection;
    public DamageReportRepository(){
        connection = ConnectionManager.getConnection();
    }

    public List<DamageReport> showAllDamageReports(){
        List<DamageReport> reportList = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int damageReportID = rs.getInt(1);
                int leaseID = rs.getInt(2);
                int vognNummer = rs.getInt(3);
                int employeeID = rs.getInt(4);

                reportList.add(new DamageReport(damageReportID,leaseID,vognNummer,employeeID));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return reportList;

    }

    public List<DamageReport> showCompleteDamageReportByLeaseID(int leaseID){
        List<DamageReport> completeReportList = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT SpecificDamage.DamageReport_damageReportID, Cars.vognNummer," +
                "Cars.stelNummer,Cars.brand,Cars.model,Cars.price, Employee.firstName, Employee.lastName," +
                "Leases.leaseID,Leases.customerFirstName,Leases.customerLastName,Leases.leasePeriodInDays," +
                "Leases.startDate,Leases.endDate,SpecificDamage.specificDamageID,SpecificDamage.title," +
                "SpecificDamage.description,SpecificDamage.picture,SpecificDamage.price," +
                "FROM DamageReport" +
                "JOIN SpecificDamage ON SpecificDamage.DamageReport_damageReportID = DamageReport.damageReportID" +
                "JOIN Leases ON Leases.leaseID = DamageReport.Leases_leaseID" +
                "JOIN Cars ON Cars.vognNummer = DamageReport.Cars_vognNummer" +
                "JOIN Employee ON Employee.EmployeeID = DamageReport.Employee_employeeID" +
                "WHERE Leases.leaseID = '"+leaseID+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int damageReportID = rs.getInt(1);
                int employeeID = rs.getInt(2);
                int leaseid = rs.getInt(3);
                int vognNummer = rs.getInt(4);


                completeReportList.add(new DamageReport(damageReportID,employeeID,leaseid,vognNummer));

            }

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return completeReportList;

    }

    public DamageReport findReportByID(int reportID){
        DamageReport report = new DamageReport();
        final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport WHERE damageReportID = '"+reportID+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int damageReportID = rs.getInt(1);
                int employeeID = rs.getInt(2);
                int leaseID = rs.getInt(3);
                int vognNummer = rs.getInt(4);

               report.setDamageReportID(damageReportID);
               report.setEmployeeID(employeeID);
               report.setLeaseID(leaseID);
               report.setVognNummer(vognNummer);

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return report;
    }



    public void addDamageReport(DamageReport damageReport){

        final String SQL_ADD_QUERY = "INSERT INTO DamageReport(DamageReportID,Leases_leaseID,Cars_vognNummer,Employee_employeeID) VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

            ps.setInt(1,damageReport.getDamageReportID());
            ps.setInt(2,damageReport.getLeaseID());
            ps.setInt(3,damageReport.getVognNummer());
            ps.setInt(4,damageReport.getEmployeeID());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke tilføje ny skaderapport");
        }

    }
    public void deleteDamageReport(int reportID){
        final String SQL_DELETE = "DELETE FROM DamageReport WHERE damageReportID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1,reportID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke slette skaderapporten");
            e.printStackTrace();
        }

    }

    public void editDamageReport(DamageReport dr){
        final String SQL_EDIT = "UPDATE DamageReport SET UserLogin_userID = ?, Leases_leaseID = ?,Cars_vognNummer = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setInt(1,dr.getEmployeeID());
            ps.setInt(2,dr.getLeaseID());
            ps.setInt(3,dr.getVognNummer());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere skaderapporten");
            e.printStackTrace();
        }

    }

}
