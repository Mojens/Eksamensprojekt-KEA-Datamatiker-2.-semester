package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/* Lavet Af Mohammed og Simon */
@Repository
public class UserRepository {
  //Laver en instance af connection i scope
  Connection connection;

  //Definer connectionens værdi som er i vores ConnectionManager
  public UserRepository() {
    connection = ConnectionManager.getConnection();
  }

  //Denne metoder opretter en user inde i tabellen
  public boolean createNewUser(User user) {
    final String QUERY = "INSERT INTO UserLogin (userID, userName, password, userType) VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, user.getUserID());
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.setInt(4, user.getType());

      preparedStatement.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kan ikke oprette bruger");
      e.printStackTrace();
      return false;
    }
  }

  //Finder specifik user objekt ud fra input som er brugernavnet
  public User findUserByUserName(String inputUserName) {

    final String QUERY = "SELECT * FROM UserLogin WHERE userName = '" + inputUserName + "'";
    User loginUser = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int type = resultSet.getInt(4);
        int status = resultSet.getInt(5);
        loginUser = new User(userID, userName, password, type, status);
      }
    } catch (SQLException e) {
      System.out.println("Could not find user");
      e.printStackTrace();
    }
    return loginUser;
  }

  //Denne metoder finder en user objekt ud fra et employee objekt
  public User findUserByEmployee(Employee employee) {

    final String QUERY = "SELECT * FROM UserLogin WHERE userID = '" + employee.getUserID() + "'";
    User loginUser = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int type = resultSet.getInt(4);
        loginUser = new User(userID, userName, password, type);
      }
    } catch (SQLException e) {
      System.out.println("Could not find user");
      e.printStackTrace();
    }
    return loginUser;
  }

  //Denne metode finder et user objekt ud fra id
  public User findUserByID(int id) {

    final String QUERY = "SELECT * FROM UserLogin WHERE userID = '" + id + "'";
    User loginUser = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int type = resultSet.getInt(4);
        int status = resultSet.getInt(5);
        loginUser = new User(userID, userName, password, type,status);
      }
    } catch (SQLException e) {
      System.out.println("Could not find user");
      e.printStackTrace();
    }
    return loginUser;
  }

  //Denne metode henter alle users der er i tabellen og putter dem ind i en liste
  public List<User> showAllUsers() {
    List<User> userList = new ArrayList<>();
    final String QUERY = "SELECT * FROM UserLogin";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int userID = resultSet.getInt(1);
        String userName = resultSet.getString(2);
        String password = resultSet.getString(3);
        int userType = resultSet.getInt(4);

        userList.add(new User(userID, userName, password, userType));

      }

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle brugere");
      e.printStackTrace();
    }
    return userList;
  }

  //Denne metode bruges til at ændre en adgangskode
  public void changePassword(String inputUserName, String newPassword, User user) {
    final String QUERY = "UPDATE UserLogin SET password = " + "'" + newPassword + "'" + " WHERE userName = " + "'" + inputUserName + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println("Kunne ikke skifte adgangskode ");
      e.printStackTrace();
    }
  }

  //Denne metode ændre status på user til 0 så den ikke er aktiv
  public void ChangeStatusUserByID(int inputUserID) {
    final String QUERYDELETE = "UPDATE UserLogin SET UserLogin.status = 0 WHERE userID = "+"'"+inputUserID+"'";
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

  //Denne metode ændre status for user til 1 så den er aktiv
  public void changeStatusForUserByIDToOne(int inputUserID) {
    final String QUERYDELETE = "UPDATE UserLogin SET UserLogin.status = 1 WHERE userID = "+"'"+inputUserID+"'";
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
}
