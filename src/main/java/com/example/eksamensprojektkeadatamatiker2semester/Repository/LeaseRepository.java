package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaseRepository {
    Connection connection;
    public LeaseRepository(){
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
                int vognNummer = rs.getInt(6);
                int startDate = rs.getInt(7);
                int endDate = rs.getInt(8);

                leasesList.add(new Lease(leaseID, firstName, lastName, leasePeriodInDays,userID,vognNummer,startDate,endDate));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle leases");
            e.printStackTrace();
        }
        return leasesList;

    }

    public List<Lease> findLeaseByID(int id){
        List<Lease> leasesList = new ArrayList<>();
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
                int vognNummer = rs.getInt(6);
                int startDate = rs.getInt(7);
                int endDate = rs.getInt(8);

                leasesList.add(new Lease(leaseID, firstName, lastName, leasePeriodInDays,userID,vognNummer,startDate,endDate));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leasesList;

    }

    public void addLease(Lease lease){
        final String SQL_ADD_QUERY = "INSERT INTO Leases(leaseID,customerFirstName,customerLastName,leasePeriodInDays,UserLogin_userID,Cars_vognNummer,startDate,endDate) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);
            ps.setInt(1,lease.getLeaseID());
            ps.setString(2,lease.getFirstName());
            ps.setString(3,lease.getLastName());
            ps.setInt(4,lease.getLeasePeriodInDays());
            ps.setInt(5,lease.getUserID());
            ps.setInt(6,lease.getVognNummer());
            ps.setInt(7,lease.getStartDate());
            ps.setInt(8,lease.getEndDate());

            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Kunne ikke oprette en lease");
            e.printStackTrace();
        }

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
        final String SQL_EDIT = "UPDATE Leases SET customerFirstName = ?, customerLastName = ?, leasePeriodInDays = ?, UserLogin_userID = ?, Cars_vognNummer = ?, startDate = ?, endDate = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setString(1,lease.getFirstName());
            ps.setString(2,lease.getLastName());
            ps.setInt(3,lease.getLeasePeriodInDays());
            ps.setInt(4,lease.getUserID());
            ps.setInt(5,lease.getVognNummer());
            ps.setInt(6,lease.getStartDate());
            ps.setInt(7,lease.getEndDate());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere leasen");
            e.printStackTrace();
        }

    }

}
