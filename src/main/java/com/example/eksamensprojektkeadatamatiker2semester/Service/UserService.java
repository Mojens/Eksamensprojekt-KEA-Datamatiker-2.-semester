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
    if (Integer.parseInt(type) == 1 || Integer.parseInt(type) == 4){
      return "redirect:/registrerLejeAftaler";
    }else if (Integer.parseInt(type) == 2 || Integer.parseInt(type) == 4){
      return "redirect:/registrerFejlOgMangel";
    }else if (Integer.parseInt(type) == 3 || Integer.parseInt(type) == 4){
      return "redirect:/showKPI";
    }else if (Integer.parseInt(type) == 4){
      return "redirect:/admin";
    }
    return "redirect:/login";
  }
  public String validateUserAccess(String type){
    if (Integer.parseInt(type) == 1 || Integer.parseInt(type) == 4){
      return "/registrerLejeAftaler";
    }else if (Integer.parseInt(type) == 2 || Integer.parseInt(type) == 4){
      return "/registrerFejlOgMangel";
    }else if (Integer.parseInt(type) == 3 || Integer.parseInt(type) == 4){
      return "/showKPI";
    }else if (Integer.parseInt(type) == 4){
      return "/admin";
    }
    return "/login";
  }

}
