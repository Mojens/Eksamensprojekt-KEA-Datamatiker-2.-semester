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

@Repository
public class EmployeeRepository {
  Connection connection;
  public EmployeeRepository(){
    connection = ConnectionManager.getConnection();
  }

  //Show current Employees
  public List<Employee> showAllEmployees(){
    ArrayList <Employee> listOfEmployees = new ArrayList<>();
    final String QUERY = "SELECT * FROM Employee";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery(QUERY);

      while (resultSet.next()){
        int employeeID = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phoneNumber = resultSet.getString(4);
        String eMail = resultSet.getString(5);
        int userID = resultSet.getInt(6);

        listOfEmployees.add(new Employee(firstName,lastName,phoneNumber,employeeID,eMail,userID));

      }
      preparedStatement.close();
    }catch (SQLException e){
      System.out.println("Kunne ikke finde nogle medarbejdere");
      e.printStackTrace();
    }
    return listOfEmployees;
  }

  //find specific employee by user ID
  public Employee findEmployeeByUserID(int inputUserID){
  Employee employee = new Employee();
  final String QUERY = "SELECT * FROM Employee WHERE UserLogin_userID = "+"'"+inputUserID+"'";
  try{
    PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
    ResultSet resultSet = preparedStatement.executeQuery(QUERY);

    while (resultSet.next()){
      int employeeID = resultSet.getInt(1);
      String firstName = resultSet.getString(2);
      String lastName = resultSet.getString(3);
      String phoneNumber = resultSet.getString(4);
      String eMail = resultSet.getString(5);
      int userID = resultSet.getInt(6);

      employee.setEmployeeID(employeeID);
      employee.setFirstName(firstName);
      employee.setLastName(lastName);
      employee.setPhoneNumber(phoneNumber);
      employee.seteMail(eMail);
      employee.setUserID(userID);

    }
    preparedStatement.close();

  }catch (SQLException e){
    System.out.println("Kunne ikke finde en medarbejde med denne ID");
    e.printStackTrace();
  }
  return employee;
  }

  //Delete employee from employee table and userLogin table with inner join
  public void deleteEmployeeByID(int inputUserID){
    final String QUERYDELETE = "DELETE UserLogin,Employee FROM UserLogin INNER JOIN Employee ON UserLogin.userID = Employee.UserLogin_userID and UserLogin_userID = "+"'"+inputUserID+"'";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(QUERYDELETE);
      //Da vi har sat foreign key på update at den skal cascade og ikke restrict
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.out.println("kunne ikke slette Medarbejder fra valgte UserID");
      e.printStackTrace();
    }

  }

  public void addNewEmployee(Employee employee){
    final String QUERY = "INSERT INTO Employee (EmployeeID, firstName, lastName, phoneNumber, eMail, UserLogin_userID) VALUES (?, ?, ?, ?, ?, ?)";
    try{
      PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

      preparedStatement.setInt(1, employee.getEmployeeID());
      preparedStatement.setString(2, employee.getFirstName());
      preparedStatement.setString(3, employee.getLastName());
      preparedStatement.setString(4, employee.getPhoneNumber());
      preparedStatement.setString(5,employee.geteMail());
      preparedStatement.setInt(6,employee.getUserID());

      preparedStatement.executeUpdate();

    }catch(SQLException e){
      System.out.println("Kan ikke oprette bruger");
      e.printStackTrace();
    }

  }
}
