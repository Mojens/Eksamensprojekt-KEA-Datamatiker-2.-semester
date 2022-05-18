package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.CarsLeases;
import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaseRepository {
    Connection connection;

    public LeaseRepository() {
        connection = ConnectionManager.getConnection();
    }

    public List<Lease> showAllLeases(){
        List<Lease> leasesList = new ArrayList<>();
        final String SQL_SHOW_LEASES = "SELECT * FROM Leases";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int leasePeriodInDays = rs.getInt(4);
                int userID = rs.getInt(5);
                LocalDate startDate = rs.getDate(6).toLocalDate();
                LocalDate endDate = rs.getDate(7).toLocalDate();

                leasesList.add(new Lease(leaseID, firstName, lastName, leasePeriodInDays,userID,startDate,endDate));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle leases");
            e.printStackTrace();
        }
        return leasesList;

    }

    public Lease findLeaseByID(int id){
        Lease leases = new Lease();
        final String SQL_SHOW_LEASES = "SELECT * FROM Leases WHERE leaseID = '"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int leasePeriodInDays = rs.getInt(4);
                int userID = rs.getInt(5);
                LocalDate startDate = rs.getDate(6).toLocalDate();
                LocalDate endDate = rs.getDate(7).toLocalDate();

                leases.setLeaseID(leaseID);
                leases.setFirstName(firstName);
                leases.setLastName(lastName);
                leases.setLeasePeriodInDays(leasePeriodInDays);
                leases.setUserID(userID);
                leases.setStartDate(startDate);
                leases.setEndDate(endDate);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leases;

    }

    public CarsLeases findLeaseAndCarByID(int id){
        CarsLeases carsLeases = new CarsLeases();
        final String SQL_SHOW_LEASES = "SELECT * FROM CarsLeases WHERE Leases_leaseID = '"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt(1);
                int carID = rs.getInt(2);
                int leaseID = rs.getInt(3);

                carsLeases.setLeaseID(ID);
                carsLeases.setCarID(carID);
                carsLeases.setLeaseID(leaseID);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return carsLeases;

    }

    public List <Lease> findLeaseByIDAsList(int id){
        List<Lease>  leases = new ArrayList<>();
        final String SQL_SHOW_LEASES = "SELECT * FROM Leases WHERE leaseID = '"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int leasePeriodInDays = rs.getInt(4);
                int userID = rs.getInt(5);
                LocalDate startDate = rs.getDate(6).toLocalDate();
                LocalDate endDate = rs.getDate(7).toLocalDate();

                leases.add(new Lease(leaseID,firstName, lastName, leasePeriodInDays,userID,startDate,endDate));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leases;

    }

    public void addLease(Lease lease){
        final String SQL_ADD_QUERY = "INSERT INTO Leases(leaseID,customerFirstName,customerLastName,leasePeriodInDays,UserLogin_userID,startDate,endDate) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);
            ps.setInt(1,lease.getLeaseID());
            ps.setString(2,lease.getFirstName());
            ps.setString(3,lease.getLastName());
            ps.setInt(4,lease.getLeasePeriodInDays());
            ps.setInt(5,lease.getUserID());

            Date sqlStartDate = Date.valueOf(lease.getStartDate());
            Date sqlEndDate = Date.valueOf(lease.getEndDate());

            ps.setDate(6,sqlStartDate);
            ps.setDate(7,sqlEndDate);

            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Kunne ikke oprette en lease");
            e.printStackTrace();
        }

    }

    public List<Lease> findLeaseByLast(){
        List <Lease> leases = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM Leases ORDER BY leaseID desc";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int leasePeriodInDays = rs.getInt(4);
                int userID = rs.getInt(5);
                LocalDate startDate = rs.getDate(6).toLocalDate();
                LocalDate endDate = rs.getDate(7).toLocalDate();


                leases.add(new Lease(leaseID,firstName,lastName,leasePeriodInDays,userID,startDate,endDate));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return leases;
    }

    public void deleteLease(int id){
        final String SQL_DELETE = "DELETE FROM Leases WHERE leaseID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1,id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke slette leasen");
            e.printStackTrace();
        }

    }

    public void editLease(Lease lease){
        final String SQL_EDIT = "UPDATE Leases SET customerFirstName = ?, customerLastName = ?, leasePeriodInDays = ?, UserLogin_userID = ?, startDate = ?, endDate = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setString(1,lease.getFirstName());
            ps.setString(2,lease.getLastName());
            ps.setInt(3,lease.getLeasePeriodInDays());
            ps.setInt(4,lease.getUserID());
            Date sqlStartDate = Date.valueOf(lease.getStartDate());
            Date sqlEndDate = Date.valueOf(lease.getEndDate());
            ps.setDate(5,sqlStartDate);
            ps.setDate(6,sqlEndDate);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere leasen");
            e.printStackTrace();
        }

    }

}
