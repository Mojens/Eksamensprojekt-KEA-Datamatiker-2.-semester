package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.DashboardRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
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

  ControllerService controllerService;

  public DashboardController(DashboardRepository dashboardRepository,
                             DashboardService dashboardService,
                             CarRepository carRepository,
                             ControllerService controllerService) {
    this.dashboardRepository = dashboardRepository;
    this.dashboardService = dashboardService;
    this.carRepository = carRepository;
    this.controllerService = controllerService;

  }

  @GetMapping("/dashboard")
  public String showKPI(Model model,
                        HttpSession httpSession) {

    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    List<Car> brandModel = dashboardRepository.brandModelList();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCar(leasedCars);
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);

    return controllerService.dashboard(httpSession);
  }


}
