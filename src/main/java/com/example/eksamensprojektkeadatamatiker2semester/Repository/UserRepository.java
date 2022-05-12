package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
  Connection connection;
  public UserRepository(){
    connection = ConnectionManager.getConnection();
  }

  //Create a new user to the system from admin site
  //We made it return a User Object beacuse to create a employee we need the auto created id as a foreign key in the other table
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

  //Find specific user object by input
  public User findUserByUserName(String inputUserName){

    final String QUERY = "SELECT * FROM UserLogin WHERE userName = '"+inputUserName+"'";
    User loginUser = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int type= resultSet.getInt(4);
        loginUser = new User(userID,userName,password,type);
      }
    } catch (SQLException e) {
      System.out.println("Could not find user");
      e.printStackTrace();
    }
    return loginUser;
  }

  //Show all the created users in DB
  public List<User> showAllUsers() {
    List<User> userList = new ArrayList<>();
    final String QUERY = "SELECT * FROM UserLogin";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int userType = resultSet.getInt(4);

        userList.add(new User(userID,userName,password,userType));

      }

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle brugere");
      e.printStackTrace();
    }
    return userList;
  }

}
