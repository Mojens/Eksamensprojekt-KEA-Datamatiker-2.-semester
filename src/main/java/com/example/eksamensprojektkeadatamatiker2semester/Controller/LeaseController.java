package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.*;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
/* Lavet Af Malthe og Mohammed */
@Controller
public class LeaseController {

    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    SpecificDamageRepository specificDamageRepository;
    DamageReportRepository damageReportRepository;
    LeaseRepository leaseRepository;
    ControllerService controllerService;
    UserRepository userRepository;
    LeaseService leaseService;
    CarsLeasesRepository carsLeasesRepository;


    public LeaseController(EmployeeRepository employeeRepository, CarRepository carRepository,
                           SpecificDamageRepository specificDamageRepository, DamageReportRepository damageReportRepository,
                           LeaseRepository leaseRepository, ControllerService controllerService,
                           UserRepository userRepository, LeaseService leaseService,CarsLeasesRepository carsLeasesRepository) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
        this.controllerService = controllerService;
        this.userRepository = userRepository;
        this.leaseService = leaseService;
        this.carsLeasesRepository = carsLeasesRepository;
    }

    @GetMapping("/allelejeaftaler")
    public String showAllLeases(HttpSession httpSession, Model model){

        List<Lease> lease = leaseRepository.showAllLeases();
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);
        model.addAttribute("lease",lease);
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);

        return controllerService.alleLejeAftaler(httpSession);
    }


    @GetMapping("/lejeaftale/{id}")
    public String showSpecificLease(@PathVariable("id") int id, Model model, HttpSession httpSession){



        CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(id);

        Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());

        Car car = carRepository.findCarByID(carsLeases.getCarID());
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);


       return controllerService.aftaler(httpSession);
    }

    @GetMapping("/findlejeaftale")
    public String findLeaseToMakeDamageReport(HttpSession httpSession, Model model,String keyword){

        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);

        if (keyword!=null){
            List<Lease> list = leaseRepository.findLeaseByIDAsList(Integer.parseInt(keyword));

            model.addAttribute("list",list);

        } else {
            List<Lease> list = leaseRepository.showAllLeases();
            model.addAttribute("list",list);

        }

        return controllerService.lejeAftaler(httpSession);
    }

    @GetMapping("/seaftale/{id}")
    public String showCarsAndLeases(@PathVariable("id") int id, HttpSession httpSession, Model model){

        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);


        CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(id);
        Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());
        Car car = carRepository.findCarByID(carsLeases.getCarID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);

        model.addAttribute("carLeases",carsLeases);
        model.addAttribute("lease",lease);
        model.addAttribute("car",car);
        model.addAttribute("employee",employee);


        return controllerService.seAftale(httpSession);
    }

    @GetMapping("/opretlejeaftale")
    public String viewOpretlejeaftale(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        //System.out.println(user);
        Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
        //System.out.println(employee);
        List<Car> listOfAvaibleCars = carRepository.showAllAvaibleCars();



        model.addAttribute("user",user);
        model.addAttribute("listOfAvaibleCars",listOfAvaibleCars);

        return controllerService.opretLejeAftaler(httpSession);
    }


    @PostMapping("/opretlejeaftale")
    public String addLease(@RequestParam("firstName")String firstName,
                           @RequestParam("lastName")String lastName,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           @RequestParam("vognNummer") int vognNummer,
                           Model model, HttpSession httpSession){


        User user = (User) httpSession.getAttribute("user");
        //System.out.println(user);
        Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
        //System.out.println(employee);

        LocalDate startDateLD = leaseService.convertToLocalDate(startDate);
        LocalDate endDateLD = leaseService.convertToLocalDate(endDate);

        leaseRepository.addLease(new Lease(firstName,lastName,user.getUserID(),startDateLD,endDateLD,1));

        List<Lease> listOfLeases = leaseRepository.findLeaseByLast();

        int leaseID = listOfLeases.get(0).getLeaseID();

        CarsLeases newCarLease = new CarsLeases(vognNummer,leaseID);

        carsLeasesRepository.addCarsLease(newCarLease);

        carsLeasesRepository.isLeased(vognNummer);



        model.addAttribute("user",user);
        model.addAttribute("employee",employee);

        return "redirect:allelejeaftaler";

    }

    @GetMapping("/sletaftale/{leaseID}")
    public String deleteEmployee(@PathVariable("leaseID") int leaseID) {
        CarsLeases selectedCarLease;
        //Skifter status på car fra 1 til 0
        selectedCarLease = carsLeasesRepository.findCarsLeasesByLeaseID(leaseID);
        carsLeasesRepository.isNotLeased(selectedCarLease.getCarID());
        //Sletter fra Leases og CarLeases
        leaseRepository.ChangeStatusLeaseByID(leaseID);

        return "redirect:/allelejeaftaler";
    }
}
