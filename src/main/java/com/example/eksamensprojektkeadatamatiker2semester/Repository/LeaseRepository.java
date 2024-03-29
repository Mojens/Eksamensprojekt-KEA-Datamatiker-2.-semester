package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
/* Lavet Af Simon, Malthe og Mohammed */
@Repository
public class LeaseRepository {
    //Laver en instance af connection i scope
    Connection connection;

    //Definer connectionens værdi som er i vores ConnectionManager
    public LeaseRepository() {
        connection = ConnectionManager.getConnection();
    }

    //Denne metoder henter alle lease aftaler og putter dem ind i en arrayListe
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
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                int status = rs.getInt(7);

                leasesList.add(new Lease(leaseID, firstName, lastName,userID,startDate,endDate,status));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle leases");
            e.printStackTrace();
        }
        return leasesList;

    }

    //Denne henter alle leases men som objekter
    public Lease showLeases(){
        Lease leases = new Lease();
        final String SQL_SHOW_LEASES = "SELECT * FROM Leases";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();

                leases.setLeaseID(leaseID);
                leases.setFirstName(firstName);
                leases.setLastName(lastName);
                leases.setUserID(userID);
                leases.setStartDate(startDate);
                leases.setEndDate(endDate);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle leases");
            e.printStackTrace();
        }
        return leases;

    }

    //Denne metoder finder en specifik lease ud fra dens id
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
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                int status = rs.getInt(7);

                leases.setLeaseID(leaseID);
                leases.setFirstName(firstName);
                leases.setLastName(lastName);
                leases.setUserID(userID);
                leases.setStartDate(startDate);
                leases.setEndDate(endDate);
                leases.setStatus(status);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leases;

    }

    //Denne finder specifik lease ud fra id men i en arrayListe
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
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();
                int status = rs.getInt(7);

                leases.add(new Lease(leaseID,firstName, lastName,userID,startDate,endDate,status));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leases;

    }

    //denne metode finder alle leases med en specifik slutdato og putter dem ind i en liste
    public List <Lease> findLeaseByDateAsList(LocalDate date){
        List<Lease>  leases = new ArrayList<>();
        final String SQL_SHOW_LEASES = "SELECT * FROM Leases WHERE endDate = '"+date+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_LEASES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();

                leases.add(new Lease(leaseID,firstName, lastName,userID,startDate,endDate));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde den lease");
            e.printStackTrace();
        }
        return leases;

    }

    //Denne metode tilføjer en lease ind i tabellen
    public boolean addLease(Lease lease){
        final String SQL_ADD_QUERY = "INSERT INTO Leases(leaseID,customerFirstName,customerLastName,UserLogin_userID,startDate,endDate) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);
            ps.setInt(1,lease.getLeaseID());
            ps.setString(2,lease.getFirstName());
            ps.setString(3,lease.getLastName());
            ps.setInt(4,lease.getUserID());
            Date sqlStartDate = Date.valueOf(lease.getStartDate());
            Date sqlEndDate = Date.valueOf(lease.getEndDate());

            ps.setDate(5,sqlStartDate);
            ps.setDate(6,sqlEndDate);

            ps.executeUpdate();
            return true;

        } catch (SQLException e){
            System.out.println("Kunne ikke oprette en lease");
            e.printStackTrace();
            return false;
        }

    }

    //Denne metode finder alle leases og putter dem ind i en arrayListe
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
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();


                leases.add(new Lease(leaseID,firstName,lastName,userID,startDate,endDate));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return leases;
    }

    //Denne metode gør at man kan ændre i en specifik lease
    public boolean editLease(Lease lease, int id){
        final String SQL_EDIT = "UPDATE Leases SET customerFirstName = ?, customerLastName = ?, UserLogin_userID = ?, startDate = ?, endDate = ?, status = ? WHERE leaseID = '"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setString(1,lease.getFirstName());
            ps.setString(2,lease.getLastName());
            ps.setInt(3,lease.getUserID());
            Date sqlStartDate = Date.valueOf(lease.getStartDate());
            Date sqlEndDate = Date.valueOf(lease.getEndDate());
            ps.setDate(4,sqlStartDate);
            ps.setDate(5,sqlEndDate);
            ps.setInt(6,lease.getStatus());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere leasen");
            e.printStackTrace();
            return false;
        }

    }

    //Denne metode ændre status på en specifik lease til 0 som er "Ikke aktiv" og det gør den ud fra en specifik leaseID
    public void ChangeStatusLeaseByID(int leaseID) {
        final String QUERYDELETE = "UPDATE Leases SET Leases.status = 0 WHERE leaseID = "+"'"+leaseID+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);
            //Da vi har sat foreign key på update at den skal cascade og ikke restrict
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("kunne ikke slette Medarbejder fra valgte UserID");
            e.printStackTrace();
        }

    }

    //Denne metode ændre status på en specifik lease til 1 som er "aktiv" og det gør den ud fra en specifik leaseID
    public void changeStatusLeaseByIDToOne(int leaseID) {
        final String QUERYDELETE = "UPDATE Leases SET Leases.status = 1 WHERE leaseID = "+"'"+leaseID+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);
            //Da vi har sat foreign key på update at den skal cascade og ikke restrict
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("kunne ikke slette Medarbejder fra valgte UserID");
            e.printStackTrace();
        }

    }

    //Denne metode finder alle leases ud fra specifik slutdato og putter dem ind i en ArrayListe
    public List<Lease> findAllLeasesByEndDate(LocalDate inputDate) {
        List<Lease> leases = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM Leases WHERE endDate = "+"'"+inputDate+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();


                leases.add(new Lease(leaseID, firstName, lastName, userID, startDate, endDate));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return leases;
    }

    //Denne metode finder alle leases med specifik startdato og putter dem ind i en ArrayListe
    public List<Lease> findAllLeasesByStartDate(LocalDate inputDate) {
        List<Lease> leases = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM Leases WHERE startDate = "+"'"+inputDate+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();


                leases.add(new Lease(leaseID, firstName, lastName, userID, startDate, endDate));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle skader");
            e.printStackTrace();
        }
        return leases;
    }

    //Denne metode finder alle leases ud fra specifik måned
    public List<Lease> findAllLeasesByCurrentMonth(int inputMonth) {
        List<Lease> leases = new ArrayList<>();
        final String SQL_SHOW_REPORT = "SELECT * FROM Leases WHERE MONTH(startDate) = "+inputMonth+" AND YEAR(startDate)=YEAR(now())";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int leaseID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int userID = rs.getInt(4);
                LocalDate startDate = rs.getDate(5).toLocalDate();
                LocalDate endDate = rs.getDate(6).toLocalDate();


                leases.add(new Lease(leaseID, firstName, lastName, userID, startDate, endDate));
                //Lease nyLease = new Lease(leaseID,firstName,lastName,userID,startDate,endDate);
                //System.out.println("Nyt lease indsat i liste "+ nyLease);
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle leases for givende måned");
            e.printStackTrace();
        }
        return leases;
    }

}
