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

    //Metoden henter og viser alle skadesrapporter, ikke selve skaderne.
    // Først henter den en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
    // Derefter henter den alle skadesrapporter fra damageReportReposi..
    // Tilsidst finder den individuelles skadesrapport status.
    // Både status og damageReports bliver tilføjet til en Model så det bliver tilgængelig på html siden.
    @GetMapping("/skaderapport")
    public String showAllDamageReports(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        DamageReport dr = new DamageReport();
        List<DamageReport> damageReports = damageReportRepository.showAllDamageReports();
        DamageReport id = damageReportRepository.findReportByID(dr.getDamageReportID());
        model.addAttribute("status", id.getStatus());
        model.addAttribute("damageReports", damageReports);


        return controllerService.skadeRapport(httpSession);
    }

    // Metoden finder en bestem lejeaftale som skal bruges til at lave en damageReport,
    // den har også en søgefunktion, hvor man kan søge efter en lejeaftale ID og derefter lave en damageReport af denne
    // 1. Den henter en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
    // 2. Den finder en damageReport ID
    // 3. Den finder status ud fra dette ID
    // 4. Alle lejeaftaler bliver vist hvor der ikke allerede er blevet lavet en DamageReport.
    // 5. If statement: Den viser den bruger søgte ID
    // 6. Hvis der ikke bliver søgt vise den alle lejeaftaler

    @GetMapping("/findlease")
    public String findLeaseToMakeDamageReport(HttpSession httpSession, Model model, String keyword) {

        DamageReport dr = new DamageReport();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        DamageReport id = damageReportRepository.findReportByID(dr.getDamageReportID());
        int statusRapport = id.getStatus();
        model.addAttribute("statusRapport", statusRapport);
        if (keyword != null) {
            List<Lease> list = leaseRepository.findLeaseByIDAsList(Integer.parseInt(keyword));
            Lease checkEndDate = leaseRepository.showLeases();
            Lease period = leaseRepository.showLeases();

            model.addAttribute("period", period);
            model.addAttribute("checkEndDate", checkEndDate);
            model.addAttribute("list", list);

        } else {
            List<Lease> list = leaseRepository.showAllLeases();
            Lease checkEndDate = leaseRepository.showLeases();
            Lease period = leaseRepository.showLeases();
            model.addAttribute("period", period);
            model.addAttribute("checkEndDate", checkEndDate);
            model.addAttribute("list", list);

        }

        return controllerService.findLease(httpSession);
    }
    // Metoden viser en bestemt lejeaftale med alt lejeaftale information, samt information på bilen der tilknyttet til denne lejeaftale.
    // 1. Den henter en user session, og så bliver den session tilføjet til en Model så den er tilgængelig på html siden.
    // 2. Den henter derefter carsLeases, altså den tabel der har information fra både Cars og Leases.
    // 3. Herefter henter den lease ID med hjælp fra carsLeases
    // 4. Henter den carID med hjælp fra carsLeases.
    // 5. Henter den employeeID fra et lease objekt.
    // Til sidst bliver alt tilføjet i en Model så det bliver tilgængeligt på bestemt html side.

    @GetMapping("/udbedring/{id}")
    public String showCarsAndLeases(@PathVariable("id") int id, HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);

        CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(id);
        Lease lease = leaseRepository.findLeaseByID(carsLeases.getLeaseID());
        Car car = carRepository.findCarByID(carsLeases.getCarID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());

        Lease checkEndDate = leaseRepository.showLeases();
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period", period);
        model.addAttribute("checkEndDate", checkEndDate);

        model.addAttribute("carLeases", carsLeases);
        model.addAttribute("lease", lease);
        model.addAttribute("car", car);
        model.addAttribute("employee", employee);


        return controllerService.udbedring(httpSession);
    }

    // Metoden viser en bestemt skadesrapport, altså uden skader i.

    @GetMapping("/skaderapport/{id}")
    public String showOneDamageReport(@PathVariable("id") int id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        DamageReport damageReports = damageReportRepository.findReportByID(id);
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period", period);
        model.addAttribute("damageReports", damageReports);

        return controllerService.skadeRapport(httpSession);
    }

    // Metoden skifter status på en skadesrapport, altså hvis bilen er klar igen til brug efter den er blevet fixet.
    @GetMapping("/changestatus/{drID}")
    public String changeStatusForDamageReport(@PathVariable("drID") int drID) {
        damageReportRepository.ChangeStatusDamageReportID(drID);

        return "redirect:/skaderapport";
    }
    // Metoden skifter status på en skadesrapport, skifter status tilbage til den er "ikke klar".
    @GetMapping("/changestatustoone/{drID}")
    public String changeStatusForDamageReportToOne(@PathVariable("drID") int drID) {
        damageReportRepository.ChangeStatusDamageReportIDToOne(drID);

        return "redirect:/skaderapport";
    }

    // Metoden opretter en ny skadesrapport, altså kun selve skadesrapporten uden skader.
    // Først finder den henholdsvis damageReportID, så leaseID, så carID og til sidst finder den employeeID
    // Derefter checker den om der allerede findes in damageReport med samme lejeaftaleID og vognNummer,
    // hvis der findes en bliver der ikke opretet en ny skadesrapport,
    // men man bliver redirectet til den allerede oprettet skadesrapport med samme lejeaftaleID og vognNummer
    // Hvis der ikke findes en skadesrapport med denne lejeaftaleID og vognNummer bliver der oprettet en ny skadesRapport
    // Man bliver til sidst redirectet til den rigtige damageReport ID efter man opretter/prøver at oprette den.

    // @RequestParam's bliver brugt til at binde en brugers input fra en form til en variable som derefter kan blive brugt i en metode.
    @PostMapping("/skaderapport")
    public String addDamageReport(@RequestParam("lejeaftaleID") int lejeaftaleID,
                                  @RequestParam("vognNummer") int vognNummer,
                                  @RequestParam("employeeID") int employeeID,
                                  Model model, HttpSession httpSession) {

        DamageReport dr = new DamageReport();
        DamageReport damageReport = damageReportRepository.findReportByID(dr.getDamageReportID());
        Lease lease = leaseRepository.findLeaseByID(damageReport.getVognNummer());
        Car car = carRepository.findCarByID(lease.getLeaseID());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());


        DamageReport checkIfExists = damageReportRepository.checkIfExists(lejeaftaleID, vognNummer);

        if (checkIfExists.getDamageReportID() == 0) {
            damageReportRepository.addDamageReport(new DamageReport(lejeaftaleID, vognNummer, employeeID));
        }

        List<DamageReport> createdDamageReport = damageReportRepository.findReportByLast();

        int id = createdDamageReport.get(0).getDamageReportID();

        model.addAttribute("car", car);
        model.addAttribute("lease", lease);
        model.addAttribute("damageReport", damageReport);

        model.addAttribute("employee", employee);
        if (checkIfExists.getDamageReportID() == 0) {
            return "redirect:skader/" + id;
        } else {
            return "redirect:skader/" + checkIfExists.getDamageReportID();
        }

    }

    // Metoden gør så man kan se en hel skadesrapport med alle skader og hvor man kan tilføje skader.
    // Først finder den henholdsvis damageReportID, så leaseID, så carID og til sidst finder den employeeID
    // Derefter finder den listen af skader tilknyttet til den bestemte skadesRapport ID
    // Derefter udregner den total prisen fra alle skader, altså summen.
    // Lease period henter alle leases, den bliver brugt på siden skader.html til at udregne lejeaftale perioden i alt i dage.
    @GetMapping("/skader/{id}")
    public String showDamageReportID(@PathVariable("id") int id, Model model, HttpSession httpSession) {

        DamageReport damageReport = damageReportRepository.findReportByID(id);

        //CarsLeases carsLeases = carsLeasesRepository.findCarsLeasesByLeaseID(damageReport.getDamageReportID());

        Lease lease = leaseRepository.findLeaseByID(damageReport.getLeaseID());

        Car car = carRepository.findCarByID(damageReport.getVognNummer());
        Employee employee = employeeRepository.findEmployeeByUserID(lease.getUserID());
        List<SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(damageReport.getDamageReportID());


        SpecificDamage sum = specificDamageRepository.sumPriceSpecificDamagesByID(id);
        Lease period = leaseRepository.showLeases();
        model.addAttribute("period", period);
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("car", car);
        model.addAttribute("lease", lease);
        model.addAttribute("damageReport", damageReport);
        model.addAttribute("employee", employee);
        model.addAttribute("specificDamage", specificDamage);
        model.addAttribute("sumTotal", sum);
        model.addAttribute("damageReportID", id);

        return controllerService.skader(httpSession);
    }

    // Metoden sletter en specifik skade og retunere derefter samme side.
    @GetMapping("/skader/{id}/{skadeID}")
    public String deleteSpecificDamage(@PathVariable("id") int id, @PathVariable("skadeID") int skadeID) {

        specificDamageRepository.deleteSpecificDamage(skadeID);

        return "redirect:/skader/" + id;
    }


}
