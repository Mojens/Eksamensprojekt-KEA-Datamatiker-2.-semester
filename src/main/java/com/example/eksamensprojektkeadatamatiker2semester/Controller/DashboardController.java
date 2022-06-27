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

/* Lavet af Mohammed og Malthe */
@Controller
//Dependency Injection - Constructor Injection
public class DashboardController {
  DashboardRepository dashboardRepository;
  DashboardService dashboardService;
  CarRepository carRepository;
  LeaseRepository leaseRepository;
  ControllerService controllerService;
  CarsLeasesRepository carsLeasesRepository;
  EmployeeRepository employeeRepository;
  DamageReportRepository damageReportRepository;

  /* Lavet Af Malthe og Mohammed */
  //Dependency Injection - Constructor Injection
  public DashboardController(DashboardRepository dashboardRepository, DashboardService dashboardService,
                             CarRepository carRepository, LeaseRepository leaseRepository, ControllerService controllerService,
                             CarsLeasesRepository carsLeasesRepository, EmployeeRepository employeeRepository, DamageReportRepository damageReportRepository) {
    this.dashboardRepository = dashboardRepository;
    this.dashboardService = dashboardService;
    this.carRepository = carRepository;
    this.leaseRepository = leaseRepository;
    this.controllerService = controllerService;
    this.carsLeasesRepository = carsLeasesRepository;
    this.employeeRepository = employeeRepository;
    this.damageReportRepository = damageReportRepository;
  }

  // Metoden viser /dashboard siden
  // Den henter en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
  // Beskrivelse på hver del findes nedenunder..
  @GetMapping("/dashboard")
    public String showKPI(Model model,
                        HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    // Denne metoder vælger alle biler der har status isleased som = 1 og smider dem ind i en liste
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    // Henter alle biler der er i tabellen som er udlejet og tilføjer dem til en Arrayliste
    List<Car> allCars = carRepository.showAllCars();
    if (allCars.isEmpty()){
      return "createcar";
    }
    DamageReport dr = new DamageReport();
    // Metoden viser alle skadesrapporter som en Liste
    List<DamageReport> damageReports = damageReportRepository.showAllDamageReports();

    // Metoden finder en skadesrapport fra et ID
    DamageReport id = damageReportRepository.findReportByID(dr.getDamageReportID());
    // Denne metode finder den brand og model som er udlejet mest og sætter den øverst i listen
    List<Car> brandModel = dashboardRepository.brandModelList();
    // Denne metode finder alle leases ud fra specifik slutdato og putter dem ind i en ArrayListe
    List<Lease> returnsToday = leaseRepository.findAllLeasesByEndDate(LocalDate.now());
    List<Car> availableCars = carRepository.showAllAvaibleCars();
    // Henter alle biler men som specifikke objekter istedet for en liste
    Car car = carRepository.showAllCarsAsObject();
    List<Lease> leaseList = leaseRepository.showAllLeases();
    List<CarsLeases> carLeasesList = carsLeasesRepository.showAllCarLeases();
    // Denne metode tæller hvor mange car objekter der i listen
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);

    // Denne metode henter total pris for alle biler i en liste
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCars(leasedCars,leaseList,carLeasesList);
    // Denne metode henter totale pris for alle biler i en liste
    double totalPriceOfAllCars = dashboardService.totalPriceLeasedCar(allCars);
    double totalPriceOfAllCar = dashboardService.totalPriceAllCars(availableCars,leaseList);
    // Denne metode tæller hvor mange bilder der er ud fra specifik model og brand og som er leased
    // Så regner vi procent og returner tal mellem 1 og 4, dette gør vi så vi kan bruge disse tal til at vise og lave farver i vores html

    int color = dashboardService.percentageStatus(allCars,leasedCars,car.getModel(),car.getBrand());
    double totalPriceLeasedCarsForColorOnly = dashboardService.totalPriceLeasedCar(leasedCars);
    // Denne metode sammenligner leased med ikke leased biler og regner procenter og returner tal
    // Så regner vi procent og returner tal mellem 1 og 4, dette gør vi så vi kan bruge disse tal til at vise og lave farver i vores html
    int colorPrice = dashboardService.percentageStatusForPriceBetweenLeasedAndNoneLeased(totalPriceOfAllCars,totalPriceLeasedCarsForColorOnly);
    //Denne metode viser dagens salg
    double todaysSale = dashboardService.todaysSale();
    //Viser måneds salgs
    double monthlySale = dashboardService.currentMonthSale(LocalDate.now().getMonth().getValue());
    //Denne metode regner om man har ramt dags budgettet
    int colorDaySale = dashboardService.percentAverageDay(todaysSale);
    //Denne metode regner om måneds salg er procentmæssigt tæt på målet
    int colorMonthSale = dashboardService.percentAverageMonth(monthlySale);
    //Denne metode ændrer de engelske måneder til danske
    String currentMonth = dashboardService.convertLocalToDanish(LocalDate.now().getMonth());
    List<DamageReport> missingHandling = damageReportRepository.showAllDamageReportsWhichNeedsHandling();
    DamageReport damageReportStatus = damageReportRepository.showAllDamageReportsAsObject();
    double averageLeasingPeriodInDays = dashboardService.averageLeasingPeriodInDays(leaseList);
    double highestLeasingPeriodInDays = dashboardService.highestLeasePeriod(leaseList);
    LocalDate currentDate = LocalDate.now();
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("thismonth",currentMonth);
    model.addAttribute("dagensDato",currentDate);
    model.addAttribute("color",color);
    model.addAttribute("colorPrice",colorPrice);
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("totalPriceOfAllCars",totalPriceOfAllCars);
    model.addAttribute("totalPriceOfAllCar",totalPriceOfAllCar);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("returnstoday",returnsToday);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);
    model.addAttribute("todaysSale",todaysSale);
    model.addAttribute("monthlySale",monthlySale);
    model.addAttribute("colorForDaySale",colorDaySale);
    model.addAttribute("colorMonthSale",colorMonthSale);
    model.addAttribute("status", id.getStatus());
    model.addAttribute("damageReports", damageReports);
    model.addAttribute("missingHandling",missingHandling);
    model.addAttribute("averagePeriodDays",averageLeasingPeriodInDays);
    model.addAttribute("damageReportStatus",damageReportStatus);
    model.addAttribute("highestLeasingPeriodInDays",highestLeasingPeriodInDays);
    model.addAttribute("pagetitle","Dashboard");

    return controllerService.dashboard(httpSession);
  }

  // Metoden er præcis som den ovenhover, dog er det en specifik side der vises når man vælger hvilken måned man vil se salg for.
  @GetMapping("/dashboard/{numberMonth}")
  public String showKPI(@PathVariable("numberMonth") int chosenMonth,
                        Model model,
                        HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
    List<Car> allCars = carRepository.showAllCars();
    if (allCars.isEmpty()){
      return "createcar";
    }
    DamageReport dr = new DamageReport();
    List<DamageReport> damageReports = damageReportRepository.showAllDamageReports();
    DamageReport id = damageReportRepository.findReportByID(dr.getDamageReportID());
    List<Car> brandModel = dashboardRepository.brandModelList();
    List<Lease> returnsToday = leaseRepository.findAllLeasesByEndDate(LocalDate.now());
    Car car = carRepository.showAllCarsAsObject();
    List<Car> availableCars = carRepository.showAllAvaibleCars();
    List<Lease> leaseList = leaseRepository.showAllLeases();
    List<CarsLeases> carLeasesList = carsLeasesRepository.showAllCarLeases();
    int amountOfLeasedCars = dashboardService.howManyisLeased(leasedCars);
    double totalPriceOfLeasedCars = dashboardService.totalPriceLeasedCars(leasedCars,leaseList,carLeasesList);
    double totalPriceLeasedCarsForColorOnly = dashboardService.totalPriceLeasedCar(leasedCars);
    double totalPriceOfAllCars = dashboardService.totalPriceLeasedCar(allCars);
    double totalPriceOfAllCar = dashboardService.totalPriceAllCars(availableCars,leaseList);
    int colorPrice = dashboardService.percentageStatusForPriceBetweenLeasedAndNoneLeased(totalPriceOfAllCars,totalPriceLeasedCarsForColorOnly);
    int color = dashboardService.percentageStatus(allCars,leasedCars,car.getModel(),car.getBrand());
    double todaysSale = dashboardService.todaysSale();
    double monthlySale = dashboardService.currentMonthSale(chosenMonth);
    int colorDaySale = dashboardService.percentAverageDay(todaysSale);
    int colorMonthSale = dashboardService.percentAverageMonth(monthlySale);
    String currentMonth = dashboardService.convertLocalToDanish(LocalDate.now().getMonth());
    String selectedMonth = dashboardService.monthByNumber(chosenMonth);
    List<DamageReport> missingHandling = damageReportRepository.showAllDamageReportsWhichNeedsHandling();
    double averageLeasingPeriodInDays = dashboardService.averageLeasingPeriodInDays(leaseList);
    double highestLeasingPeriodInDays = dashboardService.highestLeasePeriod(leaseList);
    LocalDate currentDate = LocalDate.now();
    DashboardService dashboardServices1 = new DashboardService();
    CarRepository carRepository1 = new CarRepository();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("thismonth",currentMonth);
    model.addAttribute("dagensDato",currentDate);
    model.addAttribute("color",color);
    model.addAttribute("DashboardService",dashboardServices1);
    model.addAttribute("carRep",carRepository1);
    model.addAttribute("listOfLeasedCars", leasedCars);
    model.addAttribute("totalPriceOfLeasedCars", totalPriceOfLeasedCars);
    model.addAttribute("totalPriceOfAllCars",totalPriceOfAllCars);
    model.addAttribute("totalPriceOfAllCar",totalPriceOfAllCar);
    model.addAttribute("amountOfLeasedCars", amountOfLeasedCars);
    model.addAttribute("returnstoday",returnsToday);
    model.addAttribute("allCars", allCars);
    model.addAttribute("brandModel",brandModel);
    model.addAttribute("colorPrice",colorPrice);
    model.addAttribute("todaysSale",todaysSale);
    model.addAttribute("monthlySales",monthlySale);
    model.addAttribute("selectedMonth",selectedMonth);
    model.addAttribute("colorForDaySale",colorDaySale);
    model.addAttribute("colorMonthSale",colorMonthSale);
    model.addAttribute("status", id.getStatus());
    model.addAttribute("damageReports", damageReports);
    model.addAttribute("missingHandling",missingHandling);
    model.addAttribute("averagePeriodDays",averageLeasingPeriodInDays);
    model.addAttribute("highestLeasingPeriodInDays",highestLeasingPeriodInDays);
    model.addAttribute("pagetitle","Dashboard");

    return controllerService.dashboard(httpSession);
  }

  // Metoden finder en bestem lejeaftale ud fra hvornår lejeaftalen er færdig,
  // den har også en søgefunktion, hvor man kan søge efter en bestemt dato.
  @GetMapping("/findretur")
  public String findReturn(HttpSession httpSession, Model model, String keyword){
    // Den henter en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);

    // Hvis bruger input ikke er null vises alle lejeaftaler der afsluttes på den bruger valgte dato
    if (keyword != null){
      //denne metode finder alle leases med en specifik slutdato og putter dem ind i en liste
      List<Lease> list = leaseRepository.findLeaseByDateAsList(Date.valueOf(keyword).toLocalDate());
     // Bliver brugt til at tjekke om endDatoen er ens med bruger inputs datoen. Hvis der er bliver lejeaftalen vist.
      Lease checkEndDate = leaseRepository.showLeases();
      //Denne henter alle leases men som objekter, den bliver brugt til at kunne udregne lejeaftale periode i alt i dage
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
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("pagetitle","Find Tilbageleveringer");
    return controllerService.findRetur(httpSession);
  }

  // Metoden gør så den viser siden efter man har søgt på dato og valgt en bestemt lejeaftale.
  @GetMapping("/fundetretur/{id}")
  public String showCarsAndLeases(@PathVariable("id") int id, HttpSession httpSession, Model model){
    // Den henter en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);

    // Den finder først carLeases, og så finder den carID og til sidst lejeaftleID ud fra carLeasesID
    CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(id);
    Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());
    Car car = carRepository.findCarByID(carsLeases.getCarID());
    // Bagefter finder den EmployeeID ud fra leaseID.
    Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

    // Bliver brugt til at tjekke om endDatoen er ens med bruger inputs datoen. Hvis den er bliver lejeaftalen vist.
    Lease checkEndDate = leaseRepository.showLeases();
    //Denne henter alle leases men som objekter, bliver brugt til at kunne udregne lejeaftale periode i alt i dage
    Lease period = leaseRepository.showLeases();
    Employee employeePic = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employeePic);
    model.addAttribute("period",period);
    model.addAttribute("checkEndDate",checkEndDate);
    model.addAttribute("carLeases",carsLeases);
    model.addAttribute("lease",lease);
    model.addAttribute("car",car);
    model.addAttribute("employee",employee);


    return controllerService.fundetretur(httpSession);
  }


}
