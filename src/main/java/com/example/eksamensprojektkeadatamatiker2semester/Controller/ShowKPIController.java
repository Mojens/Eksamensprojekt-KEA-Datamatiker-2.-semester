package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.ShowKPIRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ShowKPIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowKPIController {
  ShowKPIRepository showKPIRepository;
  ShowKPIService showKPIService;
  CarRepository carRepository;
  public ShowKPIController(ShowKPIRepository showKPIRepository,
                           ShowKPIService showKPIService,
                           CarRepository carRepository){
    this.showKPIRepository = showKPIRepository;
    this.showKPIService = showKPIService;
    this.carRepository = carRepository;
  }

  @GetMapping("/showKPI")
  public String showKPI(Model model,
                        HttpSession httpSession){
    List<Car> leasedCars = showKPIRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    int amountOfLeasedCars = showKPIService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = showKPIService.totalPriceLeasedCar(leasedCars);
    model.addAttribute("listOfLeasedCars",leasedCars);
    model.addAttribute("totalPriceOfLeasedCars",totalPriceOfLeasedCars);
    model.addAttribute("amountOfLeasedCars",amountOfLeasedCars);
    model.addAttribute("allCars",allCars);
    return "/showKPI";
  }



}
