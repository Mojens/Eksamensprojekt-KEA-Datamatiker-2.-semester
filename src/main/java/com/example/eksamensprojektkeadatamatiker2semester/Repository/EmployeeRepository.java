package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
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
public class EmployeeRepository {
  //Laver en instance af connection i scope
  Connection connection;

  //Definer connectionens værdi som er i vores ConnectionManager
  public EmployeeRepository() {
    connection = ConnectionManager.getConnection();
  }

  //Henter alle employees der er i tabellen og tilføjer dem til en arrayliste
  public List<Employee> showAllEmployees() {
    ArrayList<Employee> listOfEmployees = new ArrayList<>();
    final String QUERY = "SELECT * FROM Employee";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int employeeID = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phoneNumber = resultSet.getString(4);
        String eMail = resultSet.getString(5);
        int userID = resultSet.getInt(6);
        int status = resultSet.getInt(7);
        String photos = resultSet.getString(8);

        listOfEmployees.add(new Employee(firstName, lastName, phoneNumber, employeeID, eMail, userID,status,photos));

      }
      preparedStatement.close();
    } catch (SQLException e) {
      System.out.println("Kunne ikke finde nogle medarbejdere");
      e.printStackTrace();
    }
    return listOfEmployees;
  }

  //Finder employee objekt ud fra dens foreign key
  public Employee findEmployeeByUserID(int inputUserID) {
    Employee employee = new Employee();
    final String QUERY = "SELECT * FROM Employee WHERE UserLogin_userID = " + "'" + inputUserID + "'";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()) {
        int employeeID = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phoneNumber = resultSet.getString(4);
        String eMail = resultSet.getString(5);
        int userID = resultSet.getInt(6);
        int status = resultSet.getInt(7);
        String photos = resultSet.getString(8);

        employee.setEmployeeID(employeeID);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employee.seteMail(eMail);
        employee.setUserID(userID);
        employee.setStatus(status);
        employee.setPicture(photos);

      }
      preparedStatement.close();

    } catch (SQLException e) {
      System.out.println("Kunne ikke finde en medarbejde med denne ID");
      e.printStackTrace();
    }
    return employee;
  }

  //Denne metode ændrer status på specifik employee ud fra valgt user til 0 "Altså ikke aktiv"
  public void ChangeStatusEmployeeByID(int inputUserID) {
    final String QUERYDELETE = "UPDATE Employee SET Employee.status = 0 WHERE UserLogin_userID = "+"'"+inputUserID+"'";
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
  //Denne metode ændrer status på specifik employee ud fra valgt user til 1 "Altså aktiv"
  public void changeStatusEmployeeByIDToOne(int inputUserID) {
    final String QUERYDELETE = "UPDATE Employee SET Employee.status = 1 WHERE UserLogin_userID = "+"'"+inputUserID+"'";
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
//Denne metoder tilføjer en ny employee til tabellen
  public boolean addNewEmployee(Employee employee) {
    final String QUERY = "INSERT INTO Employee (EmployeeID, firstName, lastName, phoneNumber, eMail, UserLogin_userID, picture) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, employee.getEmployeeID());
      preparedStatement.setString(2, employee.getFirstName());
      preparedStatement.setString(3, employee.getLastName());
      preparedStatement.setString(4, employee.getPhoneNumber());
      preparedStatement.setString(5, employee.geteMail());
      preparedStatement.setInt(6, employee.getUserID());
      preparedStatement.setString(7,employee.getPicture());
      preparedStatement.executeUpdate();
      return true;

    } catch (SQLException e) {
      System.out.println("Kan ikke oprette bruger");
      e.printStackTrace();
      return false;
    }
  }

  public void updateEmailPhoneNumber(int inputID, String phoneNumber, String eMail, String image){
    final String UPDATE_QUERY = "UPDATE Employee SET phoneNumber = "+"'"+phoneNumber+"'"+", eMail = "+"'"+eMail+"'"+",picture = "+"'"+image+"'"+"  WHERE UserLogin_userID = "+inputID;

    try{
      PreparedStatement preparedStatementUpdateRow = connection.prepareStatement(UPDATE_QUERY);
      preparedStatementUpdateRow.executeUpdate();

    }catch (SQLException e){
      System.out.println("Kunne ikke opdater medarbejderen:"+"\n fejlen siger således: \n");
      System.out.println(e);
    }

  }
}
