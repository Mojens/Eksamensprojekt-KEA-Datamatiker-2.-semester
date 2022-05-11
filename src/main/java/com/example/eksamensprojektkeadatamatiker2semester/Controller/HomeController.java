package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
  UserService userService;
  UserRepository userRepository;

  public HomeController(UserService userService, UserRepository userRepository){
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @GetMapping("/registrerLejeAftaler")
  public String registrerLejeAftaler(HttpSession httpSession){
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null){
     currentPage = "/registrerLejeAftaler";
    }else if (httpSession.getAttribute("userName") == null){
      currentPage = "/login";
    }
    return currentPage;
  }

  @GetMapping("/registrerFejlOgMangel")
  public String registrerFejlOgMangel(HttpSession httpSession){
    String currentPage = null;
    if (httpSession.getAttribute("userName") != null){
      currentPage = "/registrerFejlOgMangel";
    }else if (httpSession.getAttribute("userName") == null){
      currentPage = "/login";
    }
    return currentPage;
  }


  @GetMapping("/logout")
  public String logOut(HttpSession httpSession){
    httpSession.removeAttribute("userName");
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/admin")
  public String admin(HttpSession httpSession){
    if (httpSession.getAttribute("userName") != null){
      return userService.checkTypeByUser((String) httpSession.getAttribute("userName"));
    }
    return "login";
  }
}
