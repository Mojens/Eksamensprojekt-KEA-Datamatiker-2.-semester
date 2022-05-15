package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.*;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
public class SpecificDamageController {
    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    SpecificDamageRepository specificDamageRepository;
    DamageReportRepository damageReportRepository;
    LeaseRepository leaseRepository;
    ControllerService controllerService;


    public SpecificDamageController(EmployeeRepository employeeRepository, CarRepository carRepository,
                                    SpecificDamageRepository specificDamageRepository, DamageReportRepository damageReportRepository,
                                    LeaseRepository leaseRepository, ControllerService controllerService) {
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.specificDamageRepository = specificDamageRepository;
        this.damageReportRepository = damageReportRepository;
        this.leaseRepository = leaseRepository;
        this.controllerService = controllerService;
    }

    @GetMapping("/fejl")
    public String viewPage(Model model){
        DamageReport damageReport = new DamageReport();

        model.addAttribute("damageReport",damageReport);
        return "/registrerFejlOgMangel";
    }

    @PostMapping("/fejl")
    public String registrerFejlOgMangel(HttpSession httpSession,

                                        @RequestParam("price") double price,
                                        @RequestParam("description")String description,
                                        @RequestParam("title") String title,
                                        @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                        Model model,
                                        @ModelAttribute("model") DamageReport report) throws IOException {

        SpecificDamage specificDamage = new SpecificDamage();
        DamageReport dr = new DamageReport();

        DamageReport damageReport = damageReportRepository.findReportByID(dr.getDamageReportID());
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        specificDamage.setPicture(fileName);

        String uploadDir = "user-photos/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        specificDamageRepository.addSpecificDamage(new SpecificDamage(price,description,fileName,title, damageReport.getDamageReportID(),lease.getLeaseID()));

        model.addAttribute("car",car);
        model.addAttribute("lease",lease);
        model.addAttribute("damageReport",damageReport);
        model.addAttribute("employee",employee);
        model.addAttribute("specificDamage",specificDamage);


        return "redirect:/skader";
        //return controllerService.registrerFejlOgMangel(httpSession);
    }



}
