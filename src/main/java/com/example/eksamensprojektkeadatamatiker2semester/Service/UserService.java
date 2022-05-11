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

  public String checkTypeByUser(String type){
    if (Integer.parseInt(type) == 1){
      return "redirect:/registrerLejeAftaler/";
    }else if (Integer.parseInt(type) == 2){
      return "redirect:/registrerFejlOgMangel";
    }else if (Integer.parseInt(type) == 3){
      return "redirect:/showKPI";
    }else if (Integer.parseInt(type) == 4){
      return "redirect:/admin";
    }
    return null;
  }


}
