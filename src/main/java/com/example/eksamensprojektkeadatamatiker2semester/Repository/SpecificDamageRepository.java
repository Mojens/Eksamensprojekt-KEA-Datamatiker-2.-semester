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
public class SpecificDamageRepository {
    Connection connection;

    public SpecificDamageRepository(){
        connection = ConnectionManager.getConnection();

    }

    public List<SpecificDamage> showAllSpecificDamages(){
        List<SpecificDamage> specificDamageList = new ArrayList<>();
        final String SQL_SHOW_DAMAGE = "SELECT * FROM SpecificDamage";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_DAMAGE);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
              int specificDamageID = rs.getInt(1);
              double price = rs.getDouble(2);
              String description = rs.getString(3);
              String picture = rs.getString(4);
              String title = rs.getString(5);
              int damageReportID = rs.getInt(6);
              int leaseID = rs.getInt(7);

              specificDamageList.add(new SpecificDamage(specificDamageID,price,description,picture,title,damageReportID,leaseID));
            }

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return specificDamageList;

    }

    public List<SpecificDamage> findSpecificDamageByReportID(int reportID){
        List<SpecificDamage> reportList = new ArrayList<>();
        final String SQL_SHOW_DAMAGE = "SELECT * FROM SpecificDamage WHERE DamageReport_damageReportID = '"+reportID+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_DAMAGE);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int specificDamageID = rs.getInt(1);
                double price = rs.getDouble(2);
                String description = rs.getString(3);
                String picture = rs.getString(4);
                String title = rs.getString(5);
                int damageReportID = rs.getInt(6);
                int leaseID = rs.getInt(7);

                reportList.add(new SpecificDamage(specificDamageID,price,description,picture,title,damageReportID,leaseID));
            }

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return reportList;

    }



    public void addSpecificDamage(SpecificDamage specificDamage){
        final String SQL_ADD_QUERY = "INSERT INTO SpecificDamage(specificDamageID,price,description,picture,title,DamageReport_damageReportID,Leases_leaseID) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);
            ps.setInt(1,specificDamage.getDamageReportID());
            ps.setDouble(2,specificDamage.getPrice());
            ps.setString(3,specificDamage.getDescription());
            ps.setString(4,specificDamage.getPicture());
            ps.setString(5,specificDamage.getTitle());
            ps.setInt(6,specificDamage.getDamageReportID());
            ps.setInt(7,specificDamage.getLeaseID());

            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Kunne ikke oprette nogen skade");
            e.printStackTrace();
        }

    }

    public void deleteSpecificDamage(int damageID){
        final String SQL_DELETE = "DELETE FROM SpecificDamage WHERE specificDamageID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1,damageID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke slette skaden");
            e.printStackTrace();
        }

    }

    public void editSpecificDamage(SpecificDamage sd){
        final String SQL_EDIT = "UPDATE SpecificDamage SET price = ?, description = ?, picture = ?, title = ?, DamageReport_damageReportID = ?, Leases_leaseID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setDouble(1,sd.getPrice());
            ps.setString(2,sd.getDescription());
            ps.setString(3,sd.getPicture());
            ps.setString(4,sd.getTitle());
            ps.setInt(5,sd.getDamageReportID());
            ps.setInt(6,sd.getLeaseID());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere");
            e.printStackTrace();
        }

    }
}
