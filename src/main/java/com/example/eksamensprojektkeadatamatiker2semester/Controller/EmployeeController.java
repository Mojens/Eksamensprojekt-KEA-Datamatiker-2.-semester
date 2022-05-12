package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
  EmployeeRepository employeeRepository;
  UserRepository userRepository;

  public EmployeeController(EmployeeRepository employeeRepository,
                            UserRepository userRepository){
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;

  }


  @PostMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam("userID") int userID){
    employeeRepository.deleteEmployeeByID(userID);
    return "redirect:/admin";
  }

  @PostMapping("/addEmployee")
  public String addEmployee(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            @RequestParam("type") int type,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("eMail") String eMail){
    userRepository.createNewUser(new User(password,userName,type));
    User createdUser = userRepository.findUserByUserName(userName);
    employeeRepository.addNewEmployee(new Employee(firstName,lastName,phoneNumber,eMail,createdUser.getUserID()));

    return "redirect:/admin";
  }


}
