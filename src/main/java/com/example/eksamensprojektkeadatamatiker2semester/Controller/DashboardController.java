package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.DashboardRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {
  DashboardRepository dashboardRepository;
  DashboardService dashboardService;
  CarRepository carRepository;
  public DashboardController(DashboardRepository dashboardRepository,
                             DashboardService dashboardService,
                             CarRepository carRepository){
    this.dashboardRepository = dashboardRepository;
    this.dashboardService = dashboardService;
    this.carRepository = carRepository;
  }

  @GetMapping("/dashboard")
  public String showKPI(Model model,
                        HttpSession httpSession){
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCar(leasedCars);
    model.addAttribute("listOfLeasedCars",leasedCars);
    model.addAttribute("totalPriceOfLeasedCars",totalPriceOfLeasedCars);
    model.addAttribute("amountOfLeasedCars",amountOfLeasedCars);
    model.addAttribute("allCars",allCars);
    return "dashboard";
  }



}
