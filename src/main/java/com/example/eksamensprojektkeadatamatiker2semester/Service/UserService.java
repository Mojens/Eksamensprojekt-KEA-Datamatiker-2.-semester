package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.springframework.stereotype.Service;
/* Lavet Af Mohammed */
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
  public String checkTypeByUser(int type){
    if (type == 1 ){
      return "redirect:opretlejeaftale";
    }else if (type == 2){
      return "redirect:findlease";
    }else if (type == 3){
      return "redirect:dashboard";
    }else if (type == 4){
      return "redirect:opretbruger";
    }
    return "login";
  }

}
