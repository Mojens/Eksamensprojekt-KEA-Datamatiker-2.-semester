package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
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
public class CarRepository {
    Connection connection;

    public CarRepository(){
        connection = ConnectionManager.getConnection();
    }

    public List<Car> showAllCars(){
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
                boolean isLeased = rs.getBoolean(6);
                int leaseID = rs.getInt(7);

                carList.add(new Car(vognNummer,stelNummer,brand,model,price,isLeased,leaseID));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde nogle biler");
            e.printStackTrace();
        }
        return carList;

    }

    public List<Car> findCarByID(int id){
        List<Car> carByIDList = new ArrayList<>();
        final String SQL_SHOW_CAR = "SELECT * FROM Cars WHERE vognNummer = '"+id+"'";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_CAR);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int vognNummer = rs.getInt(1);
                String stelNummer = rs.getString(2);
                String brand = rs.getString(3);
                String model = rs.getString(4);
                double price = rs.getDouble(5);
                boolean isLeased = rs.getBoolean(6);
                int leaseID = rs.getInt(7);

                 carByIDList.add(new Car(vognNummer,stelNummer,brand,model,price,isLeased,leaseID));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Kunne ikke finde bilen");
            e.printStackTrace();
        }
        return carByIDList;

    }

    public void addCar(Car car){
        final String SQL_ADD_QUERY = "INSERT INTO Cars(vognNummer,stelNummer,brand,model,price,isLeased,Leases_leaseID) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_QUERY);

            ps.setInt(1,car.getVognNummer());
            ps.setString(2,car.getStelNummer());
            ps.setString(3,car.getBrand());
            ps.setString(4,car.getModel());
            ps.setDouble(5,car.getPrice());
            ps.setBoolean(6,car.isLeased());
            ps.setInt(7,car.getLeaseID());

            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Kunne ikke oprette bilen");
            e.printStackTrace();
        }

    }

    public void deleteCar(int id){
        final String SQL_DELETE = "DELETE FROM Cars WHERE vognNummer = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);

            ps.setInt(1,id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke slette bilen");
            e.printStackTrace();
        }
    }

    public void editCar(Car car){
        final String SQL_EDIT = "UPDATE Cars SET stelNummer = ?, brand = ?, model = ?, price = ?, isLeased = ?, Leases_leaseID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(SQL_EDIT);

            ps.setString(1,car.getStelNummer());
            ps.setString(2,car.getBrand());
            ps.setString(3,car.getModel());
            ps.setDouble(4,car.getPrice());
            ps.setBoolean(5,car.isLeased());
            ps.setInt(6,car.getLeaseID());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kunne ikke opdatere bilen");
            e.printStackTrace();
        }

    }
}
