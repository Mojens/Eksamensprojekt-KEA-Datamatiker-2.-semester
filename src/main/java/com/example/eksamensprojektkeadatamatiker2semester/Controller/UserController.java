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

  public UserController(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @PostMapping("/login")
  public String loginValidation(@RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                HttpSession httpSession,
                                Model model) {
    User loggedUser = userRepository.findUserByUserName(userName);
    boolean isPasswordValid = userService.isPasswordValid(loggedUser, password);
    if (isPasswordValid) {
      Cookie cookieUser = new Cookie("userName", userName);
      Cookie cookieType = new Cookie("user", String.valueOf(loggedUser));
      httpSession.setAttribute("userName", cookieUser);
      httpSession.setAttribute("user", loggedUser);
      model.addAttribute("userID", loggedUser.getType());
      return userService.checkTypeByUser(String.valueOf(loggedUser.getType()));
    } else
      model.addAttribute("Failed Login", "Failed login");
    return "/redirect:/login";
  }


  @PostMapping("/createUser")
  public String createUser(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("Type") int type) {
    userRepository.createNewUser(new User(password, userName, type));
    return "redirect:/admin";
  }

  @PostMapping("/logout")
  public String logOutFunction(HttpSession httpSession) {
    httpSession.removeAttribute("userName");
    return "redirect:/login";
  }
}