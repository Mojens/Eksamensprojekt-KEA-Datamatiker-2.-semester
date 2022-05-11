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

  @GetMapping("/")
  public String index(HttpSession httpSession){
    httpSession.getAttribute("userName");
    if (httpSession.getAttribute("userName") != null){
      userService.checkTypeByUser((String) httpSession.getAttribute("userName"));
    }
    return "login";
  }

  @GetMapping("")
  public String logOut(HttpSession httpSession){
    httpSession.removeAttribute("userName");
    return "login";
  }

}
