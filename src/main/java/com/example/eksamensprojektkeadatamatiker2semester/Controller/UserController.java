package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

  UserRepository userRepository;
  UserService userService;

  @PostMapping("/login")
public String loginValidation(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              HttpSession httpSession,
                              Model model){
    User loggedUser = userRepository.findUserByUserName(userName);
    boolean isPasswordValid = userService.isPasswordValid(loggedUser,password);
    if (isPasswordValid){
      Cookie cookieUser = new Cookie("userName",userName);
      httpSession.setAttribute("userName",cookieUser);
      return "";
    }

  return "";
}
}
