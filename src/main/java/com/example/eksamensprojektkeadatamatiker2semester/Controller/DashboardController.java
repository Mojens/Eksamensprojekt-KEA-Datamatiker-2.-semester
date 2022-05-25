package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.DashboardRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.LeaseRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {
  DashboardRepository dashboardRepository;
  DashboardService dashboardService;
  CarRepository carRepository;
  LeaseRepository leaseRepository;
  ControllerService controllerService;

  public DashboardController(DashboardRepository dashboardRepository, DashboardService dashboardService,
                             CarRepository carRepository, LeaseRepository leaseRepository, ControllerService controllerService) {
    this.dashboardRepository = dashboardRepository;
    this.dashboardService = dashboardService;
    this.carRepository = carRepository;
    this.leaseRepository = leaseRepository;
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
    Car car = carRepository.showAllCarsAsObject();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCar(leasedCars);
    double totalPriceOfAllCars = dashboardService.totalPriceLeasedCar(allCars);
    int color = dashboardService.percentageStatus(allCars,leasedCars,car.getModel(),car.getBrand());
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    model.addAttribute("color",color);
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("totalPriceOfAllCars",totalPriceOfAllCars);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);

    return controllerService.dashboard(httpSession);
  }

  @GetMapping("/findretur")
  public String findReturn(HttpSession httpSession, Model model, LocalDate keyword){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    if (keyword != null){
      List<Lease> list = leaseRepository.findLeaseByIDAsList(Integer.parseInt(String.valueOf(keyword)));
      Lease checkEndDate = leaseRepository.showLeases();
      Lease period = leaseRepository.showLeases();
      model.addAttribute("period",period);
      model.addAttribute("checkEndDate",checkEndDate);
      model.addAttribute("list",list);

    } else {
      List<Lease> list = leaseRepository.showAllLeases();
      Lease checkEndDate = leaseRepository.showLeases();
      Lease period = leaseRepository.showLeases();
      model.addAttribute("period",period);
      model.addAttribute("checkEndDate",checkEndDate);
      model.addAttribute("list",list);

    }

    return controllerService.findLease(httpSession);
  }


}
