package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Model.SpecificDamage;
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
                int userID = rs.getInt(2);
                int leaseID = rs.getInt(3);

                reportList.add(new DamageReport(damageReportID,userID,leaseID));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return reportList;

    }

    public List<DamageReport> findReportByID(int reportID){
        List<DamageReport> reportList = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM DamageReport WHERE damageReportID = '"+reportID+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int damageReportID = rs.getInt(1);
                int userID = rs.getInt(2);
                int leaseID = rs.getInt(3);

                reportList.add(new DamageReport(damageReportID,userID,leaseID));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return reportList;

    }

    public void addDamageReport(DamageReport damageReport){

        final String SQL_ADD_QUERY = "INSERT INTO DamageReport(DamageReportID,UserLogin_userID,Leases_leaseID)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

            ps.setInt(1,damageReport.getDamageReportID());
            ps.setInt(2,damageReport.getUserID());
            ps.setInt(3,damageReport.getLeaseID());

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
        final String SQL_EDIT = "UPDATE DamageReport SET UserLogin_userID = ?, Leases_leaseID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setInt(1,dr.getUserID());
            ps.setInt(2,dr.getLeaseID());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere skaderapporten");
            e.printStackTrace();
        }

    }

}
