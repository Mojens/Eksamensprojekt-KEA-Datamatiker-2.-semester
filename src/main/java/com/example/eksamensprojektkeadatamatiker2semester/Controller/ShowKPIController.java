package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
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
  public ShowKPIController(ShowKPIRepository showKPIRepository,
                           ShowKPIService showKPIService){
    this.showKPIRepository = showKPIRepository;
    this.showKPIService = showKPIService;
  }

  @GetMapping("/showKPI")
  public String showKPI(Model model,
                        HttpSession httpSession){
    List<Car> leasedCars = showKPIRepository.addLeasedCarsToList();
    int amountOfLeasedCars = showKPIService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = showKPIService.totalPriceLeasedCar(leasedCars);
    System.out.println(amountOfLeasedCars);
    System.out.println(totalPriceOfLeasedCars);
    model.addAttribute("listOfLeasedCars",leasedCars);
    model.addAttribute("totalPriceOfLeasedCars",totalPriceOfLeasedCars);
    model.addAttribute("amountOfLeasedCars",amountOfLeasedCars);
    return "/showKPI";
  }



}
