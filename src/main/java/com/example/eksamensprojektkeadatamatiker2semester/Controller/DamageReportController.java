package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.*;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DamageReportController {

    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    SpecificDamageRepository specificDamageRepository;
    DamageReportRepository damageReportRepository;
    LeaseRepository leaseRepository;
    ControllerService controllerService;


    public DamageReportController(EmployeeRepository employeeRepository, CarRepository carRepository, SpecificDamageRepository specificDamageRepository, DamageReportRepository damageReportRepository, LeaseRepository leaseRepository, ControllerService controllerService) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
        this.controllerService = controllerService;
    }

    @GetMapping("/skaderapport")
    public String showAllDamageReports(HttpSession httpSession, Model model){

        List<DamageReport> damageReports = damageReportRepository.showAllDamageReports();
        model.addAttribute("damageReports",damageReports);
        return "/skaderapport";
        //return controllerService.skadeRapport(httpSession);
    }

    @PostMapping("/skaderapport")
    public String addDamageReport(@RequestParam("damageReportID") int damageReportID,
                                  @RequestParam("lejeaftaleID") int lejeaftaleID,
                                  @RequestParam("vognNummer")int vognNummer,
                                  @RequestParam("employeeID")int employeeID,
                                  Model model){

        DamageReport dr = new DamageReport();
        DamageReport damageReport = damageReportRepository.findReportByID(dr.getDamageReportID());
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

        dr.setDamageReportID(damageReportID);

        damageReportRepository.addDamageReport(new DamageReport(lejeaftaleID,vognNummer,employeeID));

        DamageReport createdDamageReport = damageReportRepository.findReportByID(dr.getDamageReportID());

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);

        return "redirect:/skaderapport/" +damageReportID;

    }

    @GetMapping("/skader/{id}")
    public String showDamageReportID(@PathVariable("id") int id, Model model, HttpSession httpSession){

        DamageReport damageReport = damageReportRepository.findReportByID(id);
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        List<SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(damageReport.getDamageReportID());

        SpecificDamage sum = specificDamageRepository.sumPriceSpecificDamagesByID(id);

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);
        model.addAttribute("specificDamage",specificDamage);
        model.addAttribute("sumTotal",sum);
        model.addAttribute("damageReportID",id);

        return "/skader";
        //return controllerService.skader(httpSession);
    }

    @GetMapping("/skaderapport/{id}")
    public String showDamageReport(@PathVariable("id") int id, Model model, HttpSession httpSession){

        DamageReport damageReport = damageReportRepository.findReportByID(id);
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        List<SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(damageReport.getDamageReportID());

        SpecificDamage sum = specificDamageRepository.sumPriceSpecificDamagesByID(id);

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);
        model.addAttribute("specificDamage",specificDamage);
        model.addAttribute("sumTotal",sum);
        model.addAttribute("damageReportID",id);

        return "/skaderapport";
        //return controllerService.skader(httpSession);
    }




}
