package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Service.UserService;
import jdk.jfr.Registered;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
/* Lavet Af Mohammed */
@Controller
public class UserController {

  UserRepository userRepository;
  UserService userService;

  EmployeeRepository employeeRepository;

  ControllerService controllerService;

  CarRepository carRepository;

  public UserController(UserRepository userRepository,
                        UserService userService,
                        EmployeeRepository employeeRepository,
                        ControllerService controllerService,
                        CarRepository carRepository) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.employeeRepository = employeeRepository;
    this.controllerService = controllerService;
    this.carRepository = carRepository;
  }

  @PostMapping("/login")
  public String loginValidation(@RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                HttpSession httpSession,
                                Model model) {
    User loggedUser = userRepository.findUserByUserName(userName);
    //Tjekker om det er en gyldig user ud fra brugernavn
    if (loggedUser != null) {
      //Tjekker om status på denne user ud fra user
      if (loggedUser.getStatus() != 0) {
       /*boolean isPasswordValid = userService.isPasswordValid(loggedUser, password);
        //Checker alm kode sammenligning for testning
        if (isPasswordValid) {
          Cookie cookieUser = new Cookie("userName", userName);
          httpSession.setAttribute("userName", cookieUser);
          httpSession.setAttribute("user", loggedUser);
          model.addAttribute("userID", loggedUser.getType());
          return userService.checkTypeByUser(loggedUser.getType());
        }//Checker for krypteret kode sammenligning*/
        if (BCrypt.checkpw(password, loggedUser.getPassword())) {
          Cookie cookieUser = new Cookie("userName", userName);
          httpSession.setAttribute("userName", cookieUser);
          httpSession.setAttribute("user", loggedUser);
          model.addAttribute("userID", loggedUser.getType());
          return userService.checkTypeByUser(loggedUser.getType());
        } else {
          model.addAttribute("wrongPWD", "Forkert adgangskode");
          return "redirect:login";
        }
      }
      model.addAttribute("notActive", "Dette er ikke en aktiv bruger");
      return "redirect:login";
    }
    model.addAttribute("noUser", "Kunne ikke finde en bruger");
    return "redirect:login";
  }

  @PostMapping("/createUser")
  public String createUser(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("Type") int type) {
    userRepository.createNewUser(new User(password, userName, type));
    return "redirect:admin";
  }

  @PostMapping("/logout")
  public String logOutFunction(HttpSession httpSession) {
    httpSession.removeAttribute("user");
    return "redirect:login";
  }

  @PostMapping("/changePassword")
  public String changePassword(@RequestParam("employeeUserID") int employeeUserID,
                               @RequestParam("newPassword") String newPassword) {
    //System.out.println(employeeUserID);
    Employee selectedEmployee = employeeRepository.findEmployeeByUserID(employeeUserID);
    //System.out.println(selectedEmployee);
    User selectedUser = userRepository.findUserByEmployee(selectedEmployee);
    //System.out.println(selectedUser);
    String bCryptPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    //System.out.println(bCryptPassword);
    userRepository.changePassword(selectedUser.getUsername(), bCryptPassword, selectedUser);
    return "redirect:skiftkode";
  }

  @GetMapping("/createcar")
  public String viewCreateCar(HttpSession httpSession,
                              Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    return controllerService.createCar(httpSession);
  }

  @PostMapping("/createnewcar")
  public String createCar(@RequestParam("stelnummer") String stelNummer,
                          @RequestParam("brand") String brand,
                          @RequestParam("model") String model,
                          @RequestParam("price") double price){

    Car createdCar = new Car(stelNummer,brand,model,price,0);
    carRepository.createNewCar(createdCar);

    return "redirect:createcar";
  }

}