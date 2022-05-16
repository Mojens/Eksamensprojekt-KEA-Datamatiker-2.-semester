package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  //Checks password
  public boolean isPasswordValid(User user, String inputPassword){
    if (inputPassword.equals(user.getPassword())){
      return true;
    }else
    return false;
  }


  //Check type by user for redirection after the login
  public String checkTypeByUser(String type){
    if (Integer.parseInt(type) == 1 ){
      return "redirect:/registrerLejeAftaler";
    }else if (Integer.parseInt(type) == 2){
      return "redirect:/registrerFejlOgMangel";
    }else if (Integer.parseInt(type) == 3){
      return "redirect:/showKPI";
    }else if (Integer.parseInt(type) == 4){
      return "redirect:/admin";
    }
    return "redirect:/login";
  }

}
