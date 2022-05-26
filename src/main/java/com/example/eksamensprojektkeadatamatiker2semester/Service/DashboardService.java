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

@Service
public class DashboardService {

  //Tjekker hvormange car objecter der er leased ud fra en liste
  public int howManyisLeased(List<Car> leasedCars) {
    int amountOfLeased = 0;
    for (Car c :
        leasedCars) {
      amountOfLeased = amountOfLeased + 1;

    }
    return amountOfLeased;
  }

  public double totalPriceLeasedCar(List<Car> leasedCars) {
    double totalPrice = 0;
    for (Car c :
        leasedCars) {
      totalPrice = totalPrice + c.getPrice();
    }
    return totalPrice;
  }

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
    int carCounter = 0;
    for (Car allCar : allCars) {
      if (allCar.getModel().equalsIgnoreCase(model.toLowerCase())) {
        if (allCar.getBrand().equalsIgnoreCase(brand.toLowerCase())) {
          carCounter = carCounter + 1;
        }
      }
    }
    int countLeased = 0;
    for (Car leasedCar : allLeasedCars) {
      if (leasedCar.getModel().equalsIgnoreCase(model.toLowerCase())) {
        if (leasedCar.getBrand().equalsIgnoreCase(brand.toLowerCase())) {
          countLeased = countLeased + 1;
        }
      }
    }
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

  public double todaysSale(){
    LeaseRepository leaseRepository = new LeaseRepository();
    CarsLeasesRepository carsLeasesRepository = new CarsLeasesRepository();
    CarRepository carRepository = new CarRepository();
    List<Lease> todaysLeases;
    List<CarsLeases> todaysCarleases;
    List<Car> carsSoldToday;
    double totalSale = 0.0;
    todaysLeases = leaseRepository.findAllLeasesByStartDate(LocalDate.now());
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
  public double currentMonthSale(int month){
    LeaseRepository leaseRepository = new LeaseRepository();
    CarsLeasesRepository carsLeasesRepository = new CarsLeasesRepository();
    CarRepository carRepository = new CarRepository();
    List<Lease> todaysLeases;
    List<CarsLeases> todaysCarleases;
    List<Car> carsSoldToday;
    double totalSale = 0.0;
    todaysLeases = leaseRepository.findAllLeasesByCurrentMonth(month);
    System.out.println(todaysLeases);
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

  public int percentageStatusForPriceBetweenLeasedAndNoneLeased(double allCars, double allLeasedCars) {

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
  
  public int percentAverageMonth(double thisMonthNumber){
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


