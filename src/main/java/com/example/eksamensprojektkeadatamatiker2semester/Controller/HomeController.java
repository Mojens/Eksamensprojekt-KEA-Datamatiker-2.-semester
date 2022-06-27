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

/* Lavet Af Mohammed */
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

  //Denne metode viser siden opretbruger
  @GetMapping("/opretbruger")
  public String admin(HttpSession httpSession,
                      Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Henter en liste med alle medarbejdere så de kan vises i html
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("listOfEmployees", listOfEmployees);
    model.addAttribute("pagetitle","Opret Bruger");
    return controllerService.opretBruger(httpSession);
  }
  //Viser siden skiftkode
  @GetMapping("/skiftkode")
  public String skiftKode(HttpSession httpSession,
                      Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    //Henter en liste med alle medarbejdere så de kan vises i html
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("listOfEmployees", listOfEmployees);
    model.addAttribute("pagetitle","Skift Kode");
    return controllerService.skiftKode(httpSession);
  }
  //Viser siden sletbruger
  @GetMapping("/sletbruger")
  public String sletBruger(HttpSession httpSession,
                          Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Henter en liste med alle medarbejdere så de kan vises i html
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("listOfEmployees", listOfEmployees);
    model.addAttribute("pagetitle","Slet Bruger");
    return controllerService.sletBruger(httpSession);
  }

  //Når man trykker på logud så skal den bruge den get mapping som redirecter tilbage til login
  @GetMapping("/logout")
  public String logOut(HttpSession httpSession){
    //Vi fjerner session på den når man klikker på logud, på denne gemmer den ikke hvem der er logget ind
    httpSession.removeAttribute("userName");
    return "login";
  }

  //Denne side viser login siden
  @GetMapping("/login")
  public String login(HttpSession httpSession){
    //Vi bruger denne metode som return værdi, så hvis man allerede har en aktiv session og prøver at komme ind på login
    //Så bliver du smidt tilbage til din forside ud fra hvilken type du har
    return controllerService.ifLoggedReturn(httpSession);
  }

  //Når man bare skriver linket ind skal du blive smidt over til login siden
  @GetMapping("/")
  public String index(HttpSession httpSession){
    return "login";
  }

  //Denne side gør at man kan se html siden lageroverblik
  @GetMapping("/lageroverblik")
  public String lagerOverblik(HttpSession httpSession,
                              Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Henter en liste med alle bilerne
    List<Car> listOfAllCars = carRepository.showAllCars();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("allCars",listOfAllCars);
    model.addAttribute("pagetitle","Lageroverblik");
    return controllerService.lagerOverblik(httpSession);
  }


}
