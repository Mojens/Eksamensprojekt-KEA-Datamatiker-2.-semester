package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CarController {

CarRepository carRepository;
ControllerService controllerService;



  public CarController(CarRepository carRepository,
                       ControllerService controllerService){

    this.carRepository = carRepository;
    this.controllerService = controllerService;
  }






  @GetMapping("/createcar")
  public String viewCreateCar(HttpSession httpSession,
                              Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    model.addAttribute("pagetitle","Opret Køretøj");
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
