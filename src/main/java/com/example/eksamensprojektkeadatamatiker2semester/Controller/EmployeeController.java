package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
  EmployeeRepository employeeRepository;

  public EmployeeController(EmployeeRepository employeeRepository){
    this.employeeRepository = employeeRepository;
  }


  @PostMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam("userID") int userID){
    employeeRepository.deleteEmployeeByID(userID);
    return "redirect:/admin";
  }



}
