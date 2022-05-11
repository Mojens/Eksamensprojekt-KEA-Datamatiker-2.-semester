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
import java.util.ArrayList;

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
      Cookie cookieType = new Cookie("cookieType",String.valueOf(loggedUser.getType()));
      httpSession.setAttribute("userName",cookieUser);
      httpSession.setAttribute("type", cookieType);
      return userService.checkTypeByUser(cookieType.getValue());
    }
model.addAttribute("Failed Login", "Failed login");
  return "/login";
}


@PostMapping("/createUser")
public String createUser(@RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("Type") int type){
    userRepository.createNewUser(new User(password,userName,type));
return "redirect:/admin";
}
}