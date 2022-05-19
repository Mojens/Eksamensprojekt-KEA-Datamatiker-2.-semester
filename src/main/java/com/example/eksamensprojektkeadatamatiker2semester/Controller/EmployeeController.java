package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.*;

@Controller
public class EmployeeController {
  EmployeeRepository employeeRepository;
  UserRepository userRepository;

  ControllerService controllerService;
  public EmployeeController(EmployeeRepository employeeRepository,
                            UserRepository userRepository,
                            ControllerService controllerService) {
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;
    this.controllerService = controllerService;
  }


  @PostMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam("userID") int userID) {
    employeeRepository.ChangeStatusEmployeeByID(userID);
    userRepository.ChangeStatusUserByID(userID);
    return "redirect:/sletbruger";
  }

  @PostMapping("/addEmployee")
  public String addEmployee(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            @RequestParam("type") int type,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("eMail") String eMail) {
    String bCryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.createNewUser(new User(bCryptPassword, userName, type,1));
    User createdUser = userRepository.findUserByUserName(userName);
    employeeRepository.addNewEmployee(new Employee(firstName, lastName, phoneNumber, eMail, createdUser.getUserID(),1));

    return "redirect:/opretbruger";
  }


}
