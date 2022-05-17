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


    public LeaseController(EmployeeRepository employeeRepository, CarRepository carRepository,
                           SpecificDamageRepository specificDamageRepository, DamageReportRepository damageReportRepository,
                           LeaseRepository leaseRepository, ControllerService controllerService,
                           UserRepository userRepository, LeaseService leaseService) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
        this.controllerService = controllerService;
        this.userRepository = userRepository;
        this.leaseService = leaseService;
    }

    @GetMapping("/allelejeaftaler")
    public String showAllLeases(HttpSession httpSession, Model model){

        List<Lease> lease = leaseRepository.showAllLeases();
        model.addAttribute("lease",lease);
        return "/allelejeaftaler";
        //return controllerService.skadeRapport(httpSession);
    }

    @GetMapping("/lejeaftale/{id}")
    public String showSpecificLease(@PathVariable("id") int id, Model model, HttpSession httpSession){



        CarsLeases carsLeases = leaseRepository.findLeaseAndCarByID(id);

        Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());

        Car car = carRepository.findCarByID(carsLeases.getCarID());


        model.addAttribute("car",car);
        model.addAttribute("lease",lease);


        return "/aftale";
        //return controllerService.skader(httpSession);
    }

    @GetMapping("/findlejeaftale")
    public String findLeaseToMakeDamageReport(HttpSession httpSession, Model model,String keyword){

        if (keyword!=null){
            List<Lease> list = leaseRepository.findLeaseByIDAsList(Integer.parseInt(keyword));

            model.addAttribute("list",list);

        } else {
            List<Lease> list = leaseRepository.showAllLeases();
            model.addAttribute("list",list);

        }

        return "/lejeaftale";
        //return controllerService.skadeRapport(httpSession);
    }

    @GetMapping("/seaftale/{id}")
    public String showCarsAndLeases(@PathVariable("id") int id, HttpSession httpSession, Model model){


        CarsLeases carsLeases = leaseRepository.findLeaseAndCarByID(id);
        Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());
        Car car = carRepository.findCarByID(carsLeases.getCarID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

        model.addAttribute("carLeases",carsLeases);
        model.addAttribute("lease",lease);
        model.addAttribute("car",car);
        model.addAttribute("employee",employee);


        return "/seaftale";
        //return controllerService.skadeRapport(httpSession);
    }

    @GetMapping("/opretlejeaftale")
    public String viewOpretlejeaftale(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        System.out.println(user);
        Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
        System.out.println(employee);


        model.addAttribute("user",user);

        return "/opretlejeaftale";
    }


    @PostMapping("/opretlejeaftale")
    public String addLease(@RequestParam("firstName")String firstName,
                           @RequestParam("lastName")String lastName,
                           @RequestParam("leasePeriod")int leasePeriod,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           Model model, HttpSession httpSession){


        User user = (User) httpSession.getAttribute("user");
        System.out.println(user);
        Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
        System.out.println(employee);

        LocalDate startDateLD = leaseService.convertToLocalDate(startDate);
        LocalDate endDateLD = leaseService.convertToLocalDate(endDate);



        leaseRepository.addLease(new Lease(firstName,lastName,leasePeriod,user.getUserID(),startDateLD,endDateLD));

        model.addAttribute("user",user);
        model.addAttribute("employee",employee);

        return "redirect:/allelejeaftaler";

    }

}
