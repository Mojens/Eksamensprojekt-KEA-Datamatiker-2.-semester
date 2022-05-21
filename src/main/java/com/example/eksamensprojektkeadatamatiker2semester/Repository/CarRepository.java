package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
  Connection connection;

  public CarRepository() {
    connection = ConnectionManager.getConnection();
  }

  public List<Car> showAllCars() {
    List<Car> carList = new ArrayList<>();
    final String SQL_SHOW_CAR = "SELECT * FROM Cars";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_CAR);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int vognNummer = rs.getInt(1);
        String stelNummer = rs.getString(2);
        String brand = rs.getString(3);
        String model = rs.getString(4);
        double price = rs.getDouble(5);
        int isLeased = rs.getInt(6);


        carList.add(new Car(vognNummer, stelNummer, brand, model, price, isLeased));

      }
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle biler");
      e.printStackTrace();
    }
    return carList;

  }

  public List<Car> showAllAvaibleCars() {
    List<Car> carList = new ArrayList<>();
    final String SQL_SHOW_CAR = "SELECT * FROM Cars WHERE isLeased= '0'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_CAR);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int vognNummer = rs.getInt(1);
        String stelNummer = rs.getString(2);
        String brand = rs.getString(3);
        String model = rs.getString(4);
        double price = rs.getDouble(5);
        int isLeased = rs.getInt(6);


        carList.add(new Car(vognNummer, stelNummer, brand, model, price, isLeased));

      }
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle biler");
      e.printStackTrace();
    }
    return carList;

  }

  public Car findCarByID(int id) {
    Car car = new Car();
    final String SQL_SHOW_CAR = "SELECT * FROM Cars WHERE vognNummer = '" + id + "'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_CAR);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int vognNummer = rs.getInt(1);
        String stelNummer = rs.getString(2);
        String brand = rs.getString(3);
        String model = rs.getString(4);
        double price = rs.getDouble(5);
        int isLeased = rs.getInt(6);

        car.setVognNummer(vognNummer);
        car.setStelNummer(stelNummer);
        car.setBrand(brand);
        car.setModel(model);
        car.setPrice(price);
        car.setLeased(isLeased);

      }
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde bilen");
      e.printStackTrace();
    }
    return car;

  }

  public List<Car> findCarByLast(){
    List <Car> car = new ArrayList<>();
    final String SQL_SHOW_REPORT = "SELECT * FROM Cars ORDER BY vognNummer desc";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_REPORT);
      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        int vognNummer = rs.getInt(1);
        String stelNummer = rs.getString(2);
        String brand = rs.getString(3);
        String model = rs.getString(4);
        double price = rs.getDouble(5);
        int isLeased = rs.getInt(6);


        car.add(new Car(vognNummer,stelNummer,brand,model,price,isLeased));

      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle biler");
      e.printStackTrace();
    }
    return car;
  }

  public boolean addCar(Car car) {
    final String SQL_ADD_QUERY = "INSERT INTO Cars(vognNummer,stelNummer,brand,model,price,isLeased) VALUES(?,?,?,?,?,?)";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

      ps.setInt(1, car.getVognNummer());
      ps.setString(2, car.getStelNummer());
      ps.setString(3, car.getBrand());
      ps.setString(4, car.getModel());
      ps.setDouble(5, car.getPrice());
      ps.setInt(6, car.isLeased());

      ps.executeUpdate();

      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke oprette bilen");
      e.printStackTrace();
      return false;
    }

  }

  public boolean deleteCar(int id) {
    final String SQL_DELETE = "DELETE FROM Cars WHERE vognNummer = ?";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

      ps.setInt(1, id);

      ps.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke slette bilen");
      e.printStackTrace();
      return false;
    }
  }

  public boolean editCar(Car car, int id) {
    final String SQL_EDIT = "UPDATE Cars SET brand = ?, model = ?, price = ?, isLeased = ? WHERE vognNummer = '"+id+"'";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_EDIT);
      ps.setString(1, car.getBrand());
      ps.setString(2, car.getModel());
      ps.setDouble(3, car.getPrice());
      ps.setInt(4, car.isLeased());


      ps.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kunne ikke opdatere bilen");
      e.printStackTrace();
      return false;
    }

  }
}
