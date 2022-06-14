package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/* Lavet Af Mohammed */
@Repository
public class DashboardRepository {
  //Laver en instance af connection i scope
  Connection connection;

  //Definer connectionens værdi som er i vores ConnectionManager
  public DashboardRepository() {
    connection = ConnectionManager.getConnection();
  }

  //denne metoder vælger alle biler der er har status isleased som = 1 og smider dem ind i en liste
  public List<Car> addLeasedCarsToList() {
    List<Car> leasedCars = new ArrayList<>();
    final String QUERY = "SELECT * FROM Cars WHERE isLeased = 1";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);
      while (resultSet.next()) {
        int vognNummer = resultSet.getInt(1);
        String stelNnummer = resultSet.getString(2);
        String brand = resultSet.getString(3);
        String model = resultSet.getString(4);
        double price = resultSet.getDouble(5);
        int isLeased = resultSet.getInt(6);

        leasedCars.add(new Car(vognNummer, stelNnummer, brand, model, price, isLeased));

      }

    } catch (SQLException e) {
      System.out.print("Kunne ikke finde nogle udlejede biler");
      e.printStackTrace();
    }
    return leasedCars;
  }

  //Denne metode finder den brand og model som er udlejet mest og sætter den øverst i listen
  public List<Car> brandModelList(){
    List<Car> brandModelList = new ArrayList<>();
    final String QUERY = "SELECT brand,model, \n" +
        "-100.0 * ((count(case when isLeased = 1 then 1 end))-(count(case when isLeased = 0 or isLeased = 1 then 1 end))) / (count(case when isLeased = 0 or isLeased = 1 then 1 end)) as 'Percentage'\n" +
        "FROM Cars\n" +
        "GROUP BY brand,model\n" +
        "order by Percentage asc";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);
      while (resultSet.next()) {
        String brand = resultSet.getString(1);
        String model = resultSet.getString(2);


        brandModelList.add(new Car(brand, model));

      }

    } catch (SQLException e) {
      System.out.print("Kunne ikke finde nogle udlejede biler");
      e.printStackTrace();
    }
    return brandModelList;
  }

}
