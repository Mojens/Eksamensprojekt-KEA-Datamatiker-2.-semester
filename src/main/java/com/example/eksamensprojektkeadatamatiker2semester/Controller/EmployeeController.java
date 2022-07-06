package com.example.eksamensprojektkeadatamatiker2semester.Controller;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.EmployeeRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.UserRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Service.ControllerService;
import com.example.eksamensprojektkeadatamatiker2semester.Utility.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/* Lavet Af Mohammed */
@Controller
public class EmployeeController {
  EmployeeRepository employeeRepository;
  UserRepository userRepository;

  ControllerService controllerService;
  public EmployeeController(EmployeeRepository employeeRepository,
                            UserRepository userRepository,
                            ControllerService controllerService) {
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;
    this.controllerService = controllerService;
  }


  //Denne metode henter parameteren user id fra html siden og benytter sig af metoden Change status for både employee og user.
  //Her ændre den status for user ud fra dens primary key
  //Den ændre status for employee ved dens foreign key
  @PostMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam("userID") int userID) {
    employeeRepository.ChangeStatusEmployeeByID(userID);
    userRepository.ChangeStatusUserByID(userID);
    return "redirect:sletbruger";
  }

  //Vi henter alle nødvendige oplysninger for at oprette en user og en employee
  @PostMapping("/addEmployee")
  public String addEmployee(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            @RequestParam("type") int type,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("eMail") String eMail,
                            @RequestParam(value = "image", required = false)MultipartFile multipartFile) throws IOException {
    //Her så krypter vi den kode som er blevet indtastet
    String bCryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.createNewUser(new User(bCryptPassword, userName, type,1));
    //Laver et user objekt ud fra den bruger der lige er oprettet
    User createdUser = userRepository.findUserByUserName(userName);
    //Her laver vi en employee og bruger users id som foreign key
    //Status er automatisk 1 da den er aktiv når man opretter den
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

    Employee employee = new Employee();
    employee.setPicture(fileName);
    String newPicture = "";

    if (fileName.isEmpty()){
      employee.setPicture("profiledefault.png");
    } else {
      newPicture = fileName.replaceAll("\\s", "");
      String uploadDir = "profile-photos/";
      FileUploadUtil.saveFile(uploadDir, newPicture, multipartFile);
    }
    employeeRepository.addNewEmployee(new Employee(firstName, lastName, phoneNumber, eMail, createdUser.getUserID(),newPicture,1));

    return "redirect:opretbruger";
  }

  //Dette er for at vide profil siden,
  @GetMapping("/profile")
  public String showProfile(Model model,
                            HttpSession httpSession){
    //Vi henter den user objekt der er logget ind lige nu
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Vi finder medarbejderen info fra userens id
    Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employee);
    model.addAttribute("pagetitle","Profil");
    return controllerService.profile(httpSession);
  }

  //Denne metode viser siden alle medarbejdere
  @GetMapping("/allemedarbejdere")
  public String showWorkers(HttpSession httpSession,
                            Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Henter en liste med alle medarbejder objekter, så vi kan tilføje dem til html
    List<Employee> listOfEmployees = employeeRepository.showAllEmployees();
    Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employee);
    model.addAttribute("listOfEmployees",listOfEmployees);
    model.addAttribute("pagetitle","Alle Medarbejdere");
    return controllerService.alleMedarbejdere(httpSession);
  }
  @GetMapping("/profileedit")
  public String showProfileEdit(Model model,
                                HttpSession httpSession){
    //Vi henter den user objekt der er logget ind lige nu
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    //Vi finder medarbejderen info fra userens id
    Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());

    model.addAttribute("profile",employee);
    model.addAttribute("pagetitle","Profil");
    return controllerService.profileEdit(httpSession);
  }
  @PostMapping("/profileedit")
  public String ProfileEdit(HttpSession httpSession,
                            @RequestParam("eMail") String eMail,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam(value = "image", required = false) MultipartFile multipartFile)throws IOException {
    //Vi henter den user objekt der er logget ind lige nu
    User user = (User) httpSession.getAttribute("user");
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

    Employee employee = new Employee();
    employee.setPicture(fileName);
    String newPicture = "";

    if (fileName.isEmpty()){
      employee.setPicture("profiledefault.png");
    } else {
      newPicture = fileName.replaceAll("\\s", "");
      String uploadDir = "profile-photos/";
      FileUploadUtil.saveFile(uploadDir, newPicture, multipartFile);
    }
    employeeRepository.updateEmailPhoneNumber(user.getUserID(),phoneNumber,eMail,newPicture);
    return "redirect:profile";
  }

  @GetMapping("/nyheder")
  public String nyheder(HttpSession httpSession,
                        Model model){
    User user = (User) httpSession.getAttribute("user");
    model.addAttribute("user",user);
    Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());
    model.addAttribute("profile",employee);

    return controllerService.nyheder(httpSession);
  }
}
