package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.*;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {
  DashboardRepository dashboardRepository;
  DashboardService dashboardService;
  CarRepository carRepository;
  LeaseRepository leaseRepository;
  ControllerService controllerService;
  CarsLeasesRepository carsLeasesRepository;
  EmployeeRepository employeeRepository;

  public DashboardController(DashboardRepository dashboardRepository, DashboardService dashboardService,
                             CarRepository carRepository, LeaseRepository leaseRepository, ControllerService controllerService,
                             CarsLeasesRepository carsLeasesRepository, EmployeeRepository employeeRepository) {
    this.dashboardRepository = dashboardRepository;
    this.dashboardService = dashboardService;
    this.carRepository = carRepository;
    this.leaseRepository = leaseRepository;
    this.controllerService = controllerService;
    this.carsLeasesRepository = carsLeasesRepository;
    this.employeeRepository = employeeRepository;
  }
  @GetMapping("/dashboard")
  public String showKPI(Model model,
                        HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    List<Car> brandModel = dashboardRepository.brandModelList();
    List<Lease> returnsToday = leaseRepository.findAllLeasesByEndDate(LocalDate.now());
    Car car = carRepository.showAllCarsAsObject();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCar(leasedCars);
    double totalPriceOfAllCars = dashboardService.totalPriceLeasedCar(allCars);
    int color = dashboardService.percentageStatus(allCars,leasedCars,car.getModel(),car.getBrand());
    double todaysSale = dashboardService.todaysSale();
    double monthlySale = 0.0;
    String currentMonth = dashboardService.convertLocalToDanish(LocalDate.now().getMonth());
    LocalDate currentDate = LocalDate.now();
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    model.addAttribute("thismonth",currentMonth);
    model.addAttribute("dagensDato",currentDate);
    model.addAttribute("color",color);
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("totalPriceOfAllCars",totalPriceOfAllCars);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("returnstoday",returnsToday);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);
    model.addAttribute("todaysSale",todaysSale);
    model.addAttribute("monthlySale",monthlySale);

    return controllerService.dashboard(httpSession);
  }
  @GetMapping("/dashboard/{numberMonth}")
  public String showKPI(@PathVariable("numberMonth") int chosenMonth,
                        Model model,
                        HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    List<Car> brandModel = dashboardRepository.brandModelList();
    List<Lease> returnsToday = leaseRepository.findAllLeasesByEndDate(LocalDate.now());
    Car car = carRepository.showAllCarsAsObject();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCar(leasedCars);
    double totalPriceOfAllCars = dashboardService.totalPriceLeasedCar(allCars);
    int colorPrice = dashboardService.percentageStatusForPriceBetweenLeasedAndNoneLeased(totalPriceOfAllCars,totalPriceOfLeasedCars);
    int color = dashboardService.percentageStatus(allCars,leasedCars,car.getModel(),car.getBrand());
    double todaysSale = dashboardService.todaysSale();
    double monthlySale = dashboardService.currentMonthSale(chosenMonth);
    String currentMonth = dashboardService.convertLocalToDanish(LocalDate.now().getMonth());
    String selectedMonth = dashboardService.monthByNumber(chosenMonth);
    LocalDate currentDate = LocalDate.now();
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    model.addAttribute("thismonth",currentMonth);
    model.addAttribute("dagensDato",currentDate);
    model.addAttribute("color",color);
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("totalPriceOfAllCars",totalPriceOfAllCars);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("returnstoday",returnsToday);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);
    model.addAttribute("colorPrice",colorPrice);
    model.addAttribute("todaysSale",todaysSale);
    model.addAttribute("monthlySale",monthlySale);
    model.addAttribute("selectedMonth",selectedMonth);

    return controllerService.dashboard(httpSession);
  }

  @GetMapping("/findretur")
  public String findReturn(HttpSession httpSession, Model model, String keyword){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);

    if (keyword != null){
      List<Lease> list = leaseRepository.findLeaseByDateAsList(Date.valueOf(keyword).toLocalDate());
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

    return controllerService.findRetur(httpSession);
  }

  @GetMapping("/fundetretur/{id}")
  public String showCarsAndLeases(@PathVariable("id") int id, HttpSession httpSession, Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);

    CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(id);
    Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());
    Car car = carRepository.findCarByID(carsLeases.getCarID());
    Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

    Lease checkEndDate = leaseRepository.showLeases();
    Lease period = leaseRepository.showLeases();

    model.addAttribute("period",period);
    model.addAttribute("checkEndDate",checkEndDate);
    model.addAttribute("carLeases",carsLeases);
    model.addAttribute("lease",lease);
    model.addAttribute("car",car);
    model.addAttribute("employee",employee);


    return controllerService.fundetretur(httpSession);
  }


}
