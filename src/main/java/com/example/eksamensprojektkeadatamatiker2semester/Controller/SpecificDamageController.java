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
  public String viewPage(Model model, HttpSession httpSession) {
    DamageReport damageReport = new DamageReport();

    model.addAttribute("damageReport", damageReport);
    return controllerService.registrerFejlOgMangler(httpSession);
  }

  @PostMapping("/fejl/{id}")
  public String registrerFejlOgMangel(HttpSession httpSession,
                                      @PathVariable("id") int id,
                                      @RequestParam("price") double price,
                                      @RequestParam("description") String description,
                                      @RequestParam("title") String title,
                                      @RequestParam(value = "image", required = false) MultipartFile multipartFile,
                                      Model model,
                                      @ModelAttribute("model") DamageReport report) throws IOException {

    SpecificDamage specificDamage = new SpecificDamage();
    DamageReport dr = new DamageReport();

    DamageReport damageReport = damageReportRepository.findReportByID(id);
    Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
    Car car = carRepository.findCarByID(lease.getLeaseID());
    Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

    Lease leaseID = leaseRepository.findLeaseByID(damageReport.getLeaseID());


    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    specificDamage.setPicture(fileName);


    String newPicture = fileName.replaceAll("\\s", "");


    String uploadDir = "user-photos/";
    FileUploadUtil.saveFile(uploadDir, newPicture, multipartFile);
    System.out.println(fileName);


    specificDamageRepository.addSpecificDamage(new SpecificDamage(price, description, newPicture, title, damageReport.getDamageReportID()));

    model.addAttribute("car", car);
    model.addAttribute("lease", lease);
    model.addAttribute("leaseID", leaseID);
    model.addAttribute("damageReport", damageReport);
    model.addAttribute("employee", employee);
    model.addAttribute("specificDamage", specificDamage);


    return "redirect:/skader/" + id;
    //return controllerService.registrerFejlOgMangel(httpSession);
  }


}
