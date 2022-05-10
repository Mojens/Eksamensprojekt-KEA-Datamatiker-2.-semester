package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserService {

  //Checks password
  public boolean isPasswordValid(User user, String inputPassword){
    if (inputPassword.equals(user.getPassword())){
      return true;
    }else
    return false;
  }

  public boolean validLogin(String userName, String password){
    return false;
  }

  public int checkTypeByUser(User user){
    return user.getType();
  }


}
