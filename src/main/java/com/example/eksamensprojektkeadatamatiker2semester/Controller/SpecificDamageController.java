package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Model.SpecificDamage;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.*;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/registrerFejlOgMangel/{damageReportID}/{leaseID}")
    public String registrerFejlOgMangel(@PathVariable("damageReportID") int damageReportID,
                                        @PathVariable("leaseID") int leaseID,
                                        HttpSession httpSession,
                                        @RequestParam("price")int price,
                                        @RequestParam("description")String description,
                                        @RequestParam("title") String title,
                                        @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                        Model model) throws IOException {

        DamageReport damageReport = damageReportRepository.findReportByID(damageReportID);
        SpecificDamage specificDamage = new SpecificDamage();


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        specificDamage.setPicture(fileName);

        String uploadDir = "user-photos/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        specificDamageRepository.addSpecificDamage(new SpecificDamage(price,description,fileName,title,leaseID,damageReportID));

        model.addAttribute("damageReport",damageReport);
        model.addAttribute("specificDamage",specificDamage);


        return "/registrerFejlOgMangel";
        //return controllerService.registrerFejlOgMangel(httpSession);
    }



}
