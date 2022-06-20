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
/* Lavet Af Malthe */
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

  // Metoden bliver brugt til at redirecte til siden /skader
  @GetMapping("/fejl")
  public String viewPage(Model model, HttpSession httpSession) {
    DamageReport damageReport = new DamageReport();

    model.addAttribute("damageReport", damageReport);
    return controllerService.registrerFejlOgMangler(httpSession);
  }

  // Metoden bliver brugt til at oprette en ny skade inden i en skadesrapport.
  // @PathVariablen er en skadesrapportens ID
  // @RequestParams er til bruger input, så deres input bliver gemt i en variable og derefter bliver gemt i databasen.
  // Brugers billed upload bliver gemt i filsystemet og ikke i databasen, databasen har kun en reference med en String fileName i.
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

    // Metoden finder en skadesrapport fra et ID
    DamageReport damageReport = damageReportRepository.findReportByID(id);
    //Denne metoder finder en specifik lease ud fra dens id
    Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
    //Finder specifik bil ud fra dens primary key som er id
    Car car = carRepository.findCarByID(lease.getLeaseID());
    //Finder employee objekt ud fra dens foreign key
    Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
    //Denne metoder finder en specifik lease ud fra dens id
    Lease leaseID = leaseRepository.findLeaseByID(damageReport.getLeaseID());

    // Variablen tager imod bruger input via en @RequestParam, String fileName er navnet på filen brugeren uploader
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    // her gør vi så den ikke længere er midlertidig gemt som et MultipartFile objekt, den kan nu blive gemt i databasen
    specificDamage.setPicture(fileName);

    String newPicture = "";
    // Metoden gør så hvis der ikke bliver uploadet nogen fil/billed så bliver der vist et default billed
    if (fileName.isEmpty()){
      specificDamage.setPicture("default.png");

      // Hvis der bliver uploadet en fil, så tager den filnavnet fra "fileName" og trimmer alle mellemrum,
      // fordi browseren ikke altid viser et billed hvor filnavnet har mellemrum.
    } else {
      newPicture = fileName.replaceAll("\\s", "");
      // uploadDir er selve mappen hvor alle billederne bliver gemt.
      String uploadDir = "user-photos/";
      // Selve metoden er kun ansvarlig for at oprette mappen, hvis den ikke eksisterer,
      // og gemmer den uploadede fil fra MultipartFile-objektet til en fil i filsystemet.
      // uploadDir er selve mappen hvor filen bliver gemt
      // newPicture er navnet på filen som bliver gemt i databasen.
      // MultipartFile er en representation af en uploadet fil som er blevet modtaget i en multipart request.
      // Selve filnavnet bliver midlertidigt gemt i filsystemet via MultipartFile interfacen og derefter permanent gemt i databasen i metoden nedenunder (addSpecificDamage).
      FileUploadUtil.saveFile(uploadDir, newPicture, multipartFile);
    }

    //System.out.println(fileName);

    // Her bliver alt gemt som normalt. Det vigtigt at sige at newPicture bliver her gemt i databasen.
    // Uden denne String værdi for filens navn, ville man ikke kunne få fat på filnavnet, da MultipartFile kun midlertidigt gemmer filnavnet.
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
