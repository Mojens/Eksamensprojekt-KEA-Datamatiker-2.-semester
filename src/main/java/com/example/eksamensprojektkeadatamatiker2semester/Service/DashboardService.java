package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  }


