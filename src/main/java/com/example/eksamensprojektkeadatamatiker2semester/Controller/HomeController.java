package com.example.eksamensprojektkeadatamatiker2semester.Controller;


import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
  ControllerService controllerService;
  EmployeeRepository employeeRepository;

  public HomeController(ControllerService controllerService,
                        EmployeeRepository employeeRepository){
    this.controllerService = controllerService;
    this.employeeRepository = employeeRepository;
  }

  @GetMapping("/registrerLejeAftaler")
  public String registrerLejeAftaler(HttpSession httpSession){
    return controllerService.registrerLejeAftaler(httpSession);
  }

  @GetMapping("/registrerFejlOgMangel")
  public String registrerFejlOgMangel(HttpSession httpSession){
    return controllerService.registrerFejlOgMangel(httpSession);
  }
  @GetMapping("/admin")
  public String admin(HttpSession httpSession,
                      Model model){
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    model.addAttribute("listOfEmployees", listOfEmployees);
    return controllerService.admin(httpSession);
  }

  @GetMapping("/logout")
  public String logOut(HttpSession httpSession){
    httpSession.removeAttribute("userName");
    return "/login";
  }

  @GetMapping("/login")
  public String login(){
    return "/login";
  }

  @GetMapping("/")
  public String index(){
    return "/login";
  }

}
