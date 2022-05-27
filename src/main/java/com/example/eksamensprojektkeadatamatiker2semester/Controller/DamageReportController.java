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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Lavet Af Malthe */
@Controller
public class DamageReportController {

    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    SpecificDamageRepository specificDamageRepository;
    DamageReportRepository damageReportRepository;
    LeaseRepository leaseRepository;
    ControllerService controllerService;
    CarsLeasesRepository carsLeasesRepository;


    public DamageReportController(EmployeeRepository employeeRepository, CarRepository carRepository,
                                  SpecificDamageRepository specificDamageRepository,
                                  DamageReportRepository damageReportRepository, LeaseRepository leaseRepository,
                                  ControllerService controllerService, CarsLeasesRepository carsLeasesRepository) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
        this.controllerService = controllerService;
        this.carsLeasesRepository = carsLeasesRepository;
    }

    @GetMapping("/skaderapport")
    public String showAllDamageReports(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        List <DamageReport> damageReports = damageReportRepository.showAllDamageReports();
        model.addAttribute("damageReports",damageReports);

        return controllerService.skadeRapport(httpSession);
    }

    @GetMapping("/findlease")
    public String findLeaseToMakeDamageReport(HttpSession httpSession, Model model,String keyword){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        if (keyword != null){
            List<Lease> list = leaseRepository.findLeaseByIDAsList(Integer.parseInt(keyword));
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

    @GetMapping("/udbedring/{id}")
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


        return controllerService.udbedring(httpSession);
    }

    @GetMapping("/skaderapport/{id}")
    public String showOneDamageReport(@PathVariable("id") int id, Model model,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        DamageReport damageReports = damageReportRepository.findReportByID(id);
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);
        model.addAttribute("damageReports",damageReports);

        return controllerService.skadeRapport(httpSession);
    }

    @PostMapping("/skaderapport")
    public String addDamageReport(@RequestParam("lejeaftaleID") int lejeaftaleID,
                                  @RequestParam("vognNummer")int vognNummer,
                                  @RequestParam("employeeID")int employeeID,
                                  Model model,HttpSession httpSession) {

        DamageReport dr = new DamageReport();
        DamageReport damageReport = damageReportRepository.findReportByID(dr.getDamageReportID());
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());


        DamageReport checkIfExists = damageReportRepository.checkIfExists(lejeaftaleID,vognNummer);

        if (checkIfExists.getDamageReportID()==0){
            damageReportRepository.addDamageReport(new DamageReport(lejeaftaleID,vognNummer,employeeID));
        }

        List <DamageReport> createdDamageReport = damageReportRepository.findReportByLast();

        int id = createdDamageReport.get(0).getDamageReportID();

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);

        model.addAttribute("employee",employee);
        if (checkIfExists.getDamageReportID()==0){
            return "redirect:skader/"+id;
        } else {
            return "redirect:skader/"+checkIfExists.getDamageReportID();
        }

    }


    @GetMapping("/skader/{id}")
    public String showDamageReportID(@PathVariable("id") int id, Model model, HttpSession httpSession){

        DamageReport damageReport = damageReportRepository.findReportByID(id);

        //CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(damageReport.getDamageReportID());

        Lease lease = leaseRepository.findLeaseByID(damageReport.getLeaseID());

        Car car = carRepository.findCarByID(damageReport.getVognNummer());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        List<SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(damageReport.getDamageReportID());


        SpecificDamage sum = specificDamageRepository.sumPriceSpecificDamagesByID(id);
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period",period);
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);
        model.addAttribute("specificDamage",specificDamage);
        model.addAttribute("sumTotal",sum);
        model.addAttribute("damageReportID",id);

        return controllerService.skader(httpSession);
    }

    @GetMapping("/skader/{id}/{skadeID}")
    public String deleteSpecificDamage(@PathVariable("id") int id,@PathVariable("skadeID") int skadeID){

        specificDamageRepository.deleteSpecificDamage(skadeID);

        return "redirect:skader/" + id;
    }


}
