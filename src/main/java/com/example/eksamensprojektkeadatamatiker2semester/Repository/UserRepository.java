package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserRepository {
  Connection connection;
  public UserRepository(){
    connection = ConnectionManager.getConnection();
  }

  //Create a new user to the system from admin site
  public void createNewUser(User user){
    final String QUERY = "INSERT INTO UserLogin (userID, userName, password, userType) VALUES (?, ?, ?, ?)";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, user.getUserID());
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.setInt(4, user.getType());

      preparedStatement.executeUpdate();

    }catch(SQLException e){
      System.out.println("Kan ikke oprette bruger");
      e.printStackTrace();
    }
  }


}
