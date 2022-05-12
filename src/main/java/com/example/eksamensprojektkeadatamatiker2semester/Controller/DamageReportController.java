package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.*;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DamageReportController {

    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    SpecificDamageRepository specificDamageRepository;
    DamageReportRepository damageReportRepository;
    LeaseRepository leaseRepository;



    public DamageReportController(EmployeeRepository employeeRepository, CarRepository carRepository, SpecificDamageRepository specificDamageRepository, DamageReportRepository damageReportRepository, LeaseRepository leaseRepository) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
    }

    @GetMapping("/skade/{id}")
    public String showDamageReport(@PathVariable("id") int id, Model model){

        DamageReport damageReport = damageReportRepository.findReportByID(id);
        //Car car = carRepository.findCarByID(damageReport.getVognNummer());
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        List<SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(damageReport.getDamageReportID());

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);
        model.addAttribute("specificDamage",specificDamage);

        return "skaderapport";
    }

    /*@GetMapping("/skade")
    public */

}
