package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class UserRepository {
  Connection connection;
  public UserRepository(){
    connection = ConnectionManager.getConnection();

  }

}
