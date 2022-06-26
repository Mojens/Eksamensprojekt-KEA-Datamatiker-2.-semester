package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.CarsLeases;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarsLeasesRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
/* Lavet Af Malthe og Mohammed */
@Service
public class DashboardService {

  // Denne metode tæller hvor mange car objekter der i listen
  public int howManyisLeased(List<Car> leasedCars) {
    int amountOfLeased = 0;
    for (Car c :
        leasedCars) {
      amountOfLeased = amountOfLeased + 1;

    }
    return amountOfLeased;
  }

  //Denne metode henter totale pris for alle biler i en liste
  public double totalPriceLeasedCar(List<Car> leasedCars) {
    double totalPrice = 0;
    for (Car c :
        leasedCars) {
      totalPrice = totalPrice + c.getPrice();
    }
    return totalPrice;
  }

  public double totalPriceLeasedCars(List<Car> leasedCars, List<Lease> leases) {
    double totalPrice = 0;
    double pricePrDay = 0;

    for (Car c :
            leasedCars) {
      for (Lease l : leases) {
       pricePrDay = c.getPrice()*l.subtractDates(l.getStartDate(),l.getEndDate())/30.41;
        totalPrice = totalPrice + pricePrDay;
      }

    }
    return totalPrice;
  }

  //Denne metoder fortæller hvor mange der er af specifik brand og model
  public int howManyPerModel(List<Car> allCars, String model, String brand) {
    int counter = 0;
    for (Car c : allCars) {
      if (c.getModel().equalsIgnoreCase(model.toLowerCase())) {
        if (c.getBrand().equalsIgnoreCase(brand.toLowerCase())) {
          counter = counter + 1;
        }
      }
    }
    return counter;
  }


  public int percentageStatus(List<Car> allCars, List<Car> allLeasedCars, String model, String brand) {
    //Denne metoder tæller hvor mange biler der er ud fra specifik model og brand
    int carCounter = 0;
    for (Car allCar : allCars) {
      if (allCar.getModel().equalsIgnoreCase(model.toLowerCase())) {
        if (allCar.getBrand().equalsIgnoreCase(brand.toLowerCase())) {
          carCounter = carCounter + 1;
        }
      }
    }
    //Denne metode tæller hvor mange bilder der er ud fra specifik model og brand og som er leased
    int countLeased = 0;
    for (Car leasedCar : allLeasedCars) {
      if (leasedCar.getModel().equalsIgnoreCase(model.toLowerCase())) {
        if (leasedCar.getBrand().equalsIgnoreCase(brand.toLowerCase())) {
          countLeased = countLeased + 1;
        }
      }
    }
    //Så regner vi procent og returner tal emllem 1 og 4, dette gør vi så vi kan bruge disse tal til at vise og lave farver i vores html
    if (countLeased * 100 / carCounter <=25){
      return 4;
    }  else if (countLeased * 100 / carCounter > 25 && countLeased * 100 / carCounter  <= 50){
      return 3;
    } else if (countLeased * 100 / carCounter > 50 && countLeased * 100 / carCounter  <= 75){
      return 2;
    }else if (countLeased * 100 / carCounter > 75 && countLeased * 100 / carCounter  <= 100){
      return 1;
    }
    return 0;
  }

  //Denne metode ændrer de engelske måneder til danske
  public String convertLocalToDanish(Month localDateMonth){
    if (String.valueOf(localDateMonth).equalsIgnoreCase("January")){
      return "Januar";
    } else if (String.valueOf(localDateMonth).equalsIgnoreCase("February")) {
      return "Februar";
    }else if (String.valueOf(localDateMonth).equalsIgnoreCase("March")){
      return "Marts";
    } else if (String.valueOf(localDateMonth).equalsIgnoreCase("April")) {
      return "April";
    } else if (String.valueOf(localDateMonth).equalsIgnoreCase("May")) {
      return "Maj";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("June")){
      return "Juni";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("July")){
      return "Juli";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("August")){
      return "August";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("September")){
      return "September";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("October")){
      return "Oktober";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("November")){
      return "November";
    }else if(String.valueOf(localDateMonth).equalsIgnoreCase("December")){
      return "December";
    }
    return "Cant read Month";
  }

  //Denne metode viser dagens salg
  public double todaysSale(){
    LeaseRepository leaseRepository = new LeaseRepository();
    CarsLeasesRepository carsLeasesRepository = new CarsLeasesRepository();
    CarRepository carRepository = new CarRepository();
    List<Lease> todaysLeases;
    List<CarsLeases> todaysCarleases;
    List<Car> carsSoldToday;
    double totalSale = 0.0;
    //Først finder vi alle leases for dagensdato
    todaysLeases = leaseRepository.findAllLeasesByStartDate(LocalDate.now());
    for (Lease lease: todaysLeases) {
      // Den finder ethvert leases som startede idag og udfra leases der er i todaysleases
      todaysCarleases = carsLeasesRepository.findCarsLeasesByLeaseIDToday(lease.getLeaseID());
      for (CarsLeases carLease : todaysCarleases) {
        //Den finder ethvery car der er solgt i dag ud fra carleases car id og putter dem ind i listen
        carsSoldToday = carRepository.allCarsByID(carLease.getCarID());
        for (Car car : carsSoldToday){
          //Så regner den totale pris
          totalSale = totalSale+car.getPrice();
        }
      }
    }
    return totalSale;
  }

  //Viser måneds salgs
  public double currentMonthSale(int month){
    //Alt dette der sker her er det samme som den tidligere metode som er dagens salg
    LeaseRepository leaseRepository = new LeaseRepository();
    CarsLeasesRepository carsLeasesRepository = new CarsLeasesRepository();
    CarRepository carRepository = new CarRepository();
    List<Lease> todaysLeases;
    List<CarsLeases> todaysCarleases;
    List<Car> carsSoldToday;
    double totalSale = 0.0;
    todaysLeases = leaseRepository.findAllLeasesByCurrentMonth(month);
    //System.out.println(todaysLeases);
    for (Lease lease: todaysLeases) {
      todaysCarleases = carsLeasesRepository.findCarsLeasesByLeaseIDToday(lease.getLeaseID());
      for (CarsLeases carLease : todaysCarleases) {
        carsSoldToday = carRepository.allCarsByID(carLease.getCarID());
        for (Car car : carsSoldToday){
          totalSale = totalSale+car.getPrice();
        }
      }
    }
    return totalSale;
  }

  //Denne metode sammenligner leased med ikke leased biler og regner procenter og returner tal
  public int percentageStatusForPriceBetweenLeasedAndNoneLeased(double allCars, double allLeasedCars) {
//Så regner vi procent og returner tal mellem 1 og 4, dette gør vi så vi kan bruge disse tal til at vise og lave farver i vores html
    if (allLeasedCars * 100 / allCars <=25){
      return 4;
    }  else if (allLeasedCars * 100 / allCars > 25 && allLeasedCars * 100 / allCars  <= 50){
      return 3;
    } else if (allLeasedCars * 100 / allCars > 50 && allLeasedCars * 100 / allCars  <= 75){
      return 2;
    }else if ((allLeasedCars * 100 / allCars) > 75 && (allLeasedCars * 100 / allCars)  <= 100){
      return 1;
    }
    return 0;
  }

  // Ændrer måneds tal til måned navn
  public String monthByNumber(int month){
    if (month == 1){
      return "Januar";
    } else if (month == 2) {
      return "Februar";
    }else if (month == 3){
      return "Marts";
    } else if (month == 4) {
      return "April";
    } else if (month == 5) {
      return "Maj";
    }else if(month == 6){
      return "Juni";
    }else if(month == 7){
      return "Juli";
    }else if(month == 8){
      return "August";
    }else if(month == 9){
      return "September";
    }else if(month == 10){
      return "Oktober";
    }else if(month == 11){
      return "November";
    }else if(month == 12){
      return "December";
    }
    return "Cant read Month";
  }

  //Denne metode regner om måneds salg er procentmæssigt tæt på målet
  public int percentAverageMonth(double thisMonthNumber){
    //Så regner vi procent og returner tal mellem 1 og 4, dette gør vi så vi kan bruge disse tal til at vise og lave farver i vores html
    if (thisMonthNumber * 100 / 500000 <=25){
      return 1;
    }  else if (thisMonthNumber * 100 / 500000 > 25 && thisMonthNumber * 100 / 500000  <= 50){
      return 2;
    } else if (thisMonthNumber * 100 / 500000 > 50 && thisMonthNumber * 100 / 500000  <= 75){
      return 3;
    }else if (thisMonthNumber * 100 / 500000 > 75 && thisMonthNumber * 100 / 500000  <= 100){
      return 4;
    }
    return 0;
  }

  //Denne metode regner om man har ramt dags budgettet
  public int percentAverageDay(double thisDayNumber){
    if (thisDayNumber * 100 / 15000 <=25){
      return 1;
    }  else if (thisDayNumber * 100 / 15000 > 25 && thisDayNumber * 100 / 15000  <= 50){
      return 2;
    } else if (thisDayNumber * 100 / 15000 > 50 && thisDayNumber * 100 / 15000  <= 75){
      return 3;
    }else if (thisDayNumber * 100 / 15000 > 75 && thisDayNumber * 100 / 15000  <= 100){
      return 4;
    }
    return 0;
  }
  }


