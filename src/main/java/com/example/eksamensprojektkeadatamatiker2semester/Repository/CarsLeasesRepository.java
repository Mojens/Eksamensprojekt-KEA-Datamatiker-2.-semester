package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.CarsLeases;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* Lavet Af Mohammed */
@Repository
public class CarsLeasesRepository {
  //Laver en instance af connection i scope
  Connection connection;

  //Definer connectionens værdi som er i vores ConnectionManager
  public CarsLeasesRepository() {
    connection = ConnectionManager.getConnection();
  }

  //Denne metode tilføjer en carleases ind i tabellen
  public void addCarsLease(CarsLeases carsLeases) {
    final String QUERY = "INSERT INTO  CarsLeases (id, Cars_vognNummer, Leases_leaseID) VALUES (?, ?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, carsLeases.getId());
      preparedStatement.setInt(2, carsLeases.getCarID());
      preparedStatement.setInt(3, carsLeases.getLeaseID());

      preparedStatement.executeUpdate();


    } catch (SQLException e) {
      System.out.println("Kunne ikke tilføje til CarsLeases");
      e.printStackTrace();
    }
  }

  //Denne metoder ændre status på en car til at den er leased
  public void isLeased(int inputVognNummer) {
    final String QUERY = "UPDATE Cars SET isLeased = 1 WHERE vognNummer = " + "'" + inputVognNummer + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println("Kunne ikke ændre status til leased");
      e.printStackTrace();
    }

  }

  //Denne metoder ændre status på en car til at den ikke er leased
  public void isNotLeased(int inputVognNummer) {
    final String QUERY = "UPDATE Cars SET isLeased = 0 WHERE vognNummer = " + "'" + inputVognNummer + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println("Kunne ikke ændre status til leased");
      e.printStackTrace();
    }
  }

  //Denne metode finder en carslease ud fra en en leaseID
  public CarsLeases findCarsLeasesByLeaseID(int inputLeaseID){
    CarsLeases carsLeases = new CarsLeases();
    final String QUERY = "SELECT * FROM CarsLeases WHERE Leases_leaseID = " + "'" + inputLeaseID + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int carLeasesID = resultSet.getInt(1);
        int carID = resultSet.getInt(2);
        int leaseID = resultSet.getInt(3);

        carsLeases.setId(carLeasesID);
        carsLeases.setCarID(carID);
        carsLeases.setLeaseID(leaseID);

      }
      preparedStatement.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde en medarbejde med denne ID");
      e.printStackTrace();
    }
    return carsLeases;
  }
  //Denne metode finder alle carleases med en specifik leaseid og putter dem ind i en liste
  public List<CarsLeases> findCarsLeasesByLeaseIDToday(int inputLeaseID){
    List<CarsLeases> todayCarLeases = new ArrayList<>();
    CarsLeases carsLeases = new CarsLeases();
    final String QUERY = "SELECT * FROM CarsLeases WHERE Leases_leaseID = " + "'" + inputLeaseID + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int carLeasesID = resultSet.getInt(1);
        int carID = resultSet.getInt(2);
        int leaseID = resultSet.getInt(3);

        carsLeases.setId(carLeasesID);
        carsLeases.setCarID(carID);
        carsLeases.setLeaseID(leaseID);

      }
      todayCarLeases.add(carsLeases);
      preparedStatement.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde en medarbejde med denne ID");
      e.printStackTrace();
    }
    return todayCarLeases;
  }

  public List<CarsLeases> showAllCarLeases(){
    List<CarsLeases> list = new ArrayList<>();
    final String SQL_SHOW_CAR = "SELECT * FROM CarsLeases";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_CAR);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int carLeasesID = rs.getInt(1);
        int carID = rs.getInt(2);
        int leaseID = rs.getInt(3);


        list.add(new CarsLeases(carLeasesID, carID, leaseID));

      }
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle leases og biler");
      e.printStackTrace();
    }
    return list;
  }

}
