package com.example.eksamensprojektkeadatamatiker2semester.Controller;


import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
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
  CarRepository carRepository;

  public HomeController(ControllerService controllerService,
                        EmployeeRepository employeeRepository,
                        CarRepository carRepository){
    this.controllerService = controllerService;
    this.employeeRepository = employeeRepository;
    this.carRepository = carRepository;
  }

  @GetMapping("/opretbruger")
  public String admin(HttpSession httpSession,
                      Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    model.addAttribute("listOfEmployees", listOfEmployees);
    return controllerService.opretBruger(httpSession);
  }
  @GetMapping("/skiftkode")
  public String skiftKode(HttpSession httpSession,
                      Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    model.addAttribute("listOfEmployees", listOfEmployees);
    return controllerService.skiftKode(httpSession);
  }
  @GetMapping("/sletbruger")
  public String sletBruger(HttpSession httpSession,
                          Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    model.addAttribute("listOfEmployees", listOfEmployees);
    return controllerService.sletBruger(httpSession);
  }

  @GetMapping("/logout")
  public String logOut(HttpSession httpSession){
    httpSession.removeAttribute("userName");
    return "/login";
  }

  @GetMapping("/login")
  public String login(HttpSession httpSession){
    return controllerService.ifLoggedReturn(httpSession);
  }

  @GetMapping("/")
  public String index(HttpSession httpSession){
    return "/login";
  }

  @GetMapping("/lageroverblik")
  public String lagerOverblik(HttpSession httpSession,
                              Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Car> listOfAllCars = carRepository.showAllCars();
    model.addAttribute("allCars",listOfAllCars);
    return controllerService.lagerOverblik(httpSession);
  }


}
