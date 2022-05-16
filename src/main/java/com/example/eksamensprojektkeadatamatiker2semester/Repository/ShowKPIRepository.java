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

@Repository
public class ShowKPIRepository {
    Connection connection;

    public ShowKPIRepository(){
        connection = ConnectionManager.getConnection();
    }

    //denne metoder vælger alle biler der er har status isleased som = 1 og smider dem ind i en liste
    public List<Car> addLeasedCarsToList(){
        List<Car> leasedCars = new ArrayList<>();
        final String QUERY = "SELECT * FROM Cars WHERE isLeased = 1";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery(QUERY);
            while (resultSet.next()){
                int vognNummer = resultSet.getInt(1);
                String stelNnummer = resultSet.getString(2);
                String brand = resultSet.getString(3);
                String model = resultSet.getString(4);
                double price = resultSet.getDouble(5);
                int isLeased = resultSet.getInt(6);

                leasedCars.add(new Car(vognNummer,stelNnummer,brand,model,price,isLeased));

            }

        }catch (SQLException e){
            System.out.print("Kunne ikke finde nogle udlejede biler");
            e.printStackTrace();
        }
        return leasedCars;
    }

}
