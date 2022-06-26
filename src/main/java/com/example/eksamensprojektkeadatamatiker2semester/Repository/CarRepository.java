package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Model.SpecificDamage;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/* Lavet Af Malthe og Mohammed*/
@Repository
public class CarRepository {
  //Laver en instance af connection i scope
  Connection connection;

  //Definer connectionens værdi som er i vores ConnectionManager
  public CarRepository() {
    connection = ConnectionManager.getConnection();
  }

  //Henter alle biler der er i tabellen som er udlejet og tilføjer dem til en arrayliste
  public List<Car> showAllCars() {
    List<Car> carList = new ArrayList<>();
    final String SQL_SHOW_CAR = "SELECT * FROM Cars ORDER BY isLeased = 1";

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

  //Henter alle biler men som specifikke objekter istedet for en liste
  public Car showAllCarsAsObject() {
    Car car = new Car();
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


        car.setVognNummer(vognNummer);
        car.setStelNummer(stelNummer);
        car.setBrand(brand);
        car.setModel(model);
        car.setPrice(price);
        car.setLeased(isLeased);

      }
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle biler");
      e.printStackTrace();
    }
    return car;

  }

  //Henter alle biler der er i tabellen som ikke er udlejet og tilføjer dem til en arrayliste
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

  //Finder specifik bil ud fra dens primary key som er id
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

  //Henter alle biler og putter dem ind i arraylist men hvor det starter med dem der er flest af og så går det længere ned
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

  //Denne metoder gør så man kan tilføje en car ind i tabellen
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


  //Denne metode gør at man kan ændre i en specifik bil
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

  //Denne metode opretter en bil og sætter ind i tabellen
  public boolean createNewCar(Car car) {
    final String QUERY = "INSERT INTO Cars (vognNummer, stelNummer, brand, model, price, isLeased) VALUES (?, ?, ?, ?, ?, ?)";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, car.getVognNummer());
      preparedStatement.setString(2, car.getStelNummer());
      preparedStatement.setString(3, car.getBrand());
      preparedStatement.setString(4, car.getModel());
      preparedStatement.setDouble(5, car.getPrice());
      preparedStatement.setInt(6, car.isLeased());

      preparedStatement.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kan ikke oprette bruger");
      e.printStackTrace();
      return false;
    }

  }

  //Denne metode finder summen på bil med specifik model og brand og at den skal være leased
  public double sumPriceLeasedCars(String brandInput, String modelInput) {
    double sum = 0;
    final String SQL_SHOW_DAMAGE = "SELECT SUM(price) FROM Cars WHERE brand = "+"'"+brandInput+"'"+" AND model = "+"'"+modelInput+"'"+" AND isLeased = 1";

    try {
      PreparedStatement ps = connection.prepareStatement(SQL_SHOW_DAMAGE);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        double sumTotal = rs.getDouble(1);

        sum = sumTotal;

      }
      ps.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle skader");
      e.printStackTrace();
    }
    return sum;

  }

  //Finder alle biler med specifik vognnummer
  public List<Car> allCarsByID(int inputVognNummer){
    List<Car> soldToday = new ArrayList<>();
    Car car = new Car();
    final String SQL_SHOW_CAR = "SELECT * FROM Cars WHERE vognNummer = '" + inputVognNummer + "'";

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
      soldToday.add(car);
      ps.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde bilen");
      e.printStackTrace();
    }
    return soldToday;

  }
  }

