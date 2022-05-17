package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.CarsLeases;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
public class CarsLeasesRepository {
  Connection connection;

  public CarsLeasesRepository(){
    connection = ConnectionManager.getConnection();
  }

  public void addCarsLease(CarsLeases carsLeases){
    final String QUERY = "INSERT INTO  CarsLeases (id, Cars_vognNummer, Leases_leaseID) VALUES (?, ?, ?)";
        try{
          PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

          preparedStatement.setInt(1, carsLeases.getId());
          preparedStatement.setInt(2, carsLeases.getCarID());
          preparedStatement.setInt(3, carsLeases.getLeaseID());

          preparedStatement.executeUpdate();


        }catch (SQLException e){
          System.out.println("Kunne ikke tilføje til CarsLeases");
          e.printStackTrace();
        }
  }

}
