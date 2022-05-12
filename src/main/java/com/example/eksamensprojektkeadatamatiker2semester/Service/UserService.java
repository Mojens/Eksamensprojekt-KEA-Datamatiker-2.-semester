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

  public String validateUserForTypeOne(int type) {
    String currentPage;
    if (type == 1 || type == 4) {
      currentPage = "/registrerLejeAftaler";
      return currentPage;
    }
    return "/login";
  }

  public String validateUserForTypeTwo(int type) {
    String currentPage;
    if (type == 2 || type == 4) {
      currentPage = "/registrerFejlOgMangel";
      return currentPage;
    }
    return "/login";
  }

  public String validateUserForTypeThree(int type) {
    String currentPage;
    if (type == 3 || type == 4) {
      currentPage = "/showKPI";
      return currentPage;
    }
    return "/login";
  }

  public String validateUserForTypeFour(int type) {
    String currentPage;
    if (type == 4) {
      currentPage = "/admin";
      return currentPage;
    }
    return "/login";
  }
}
