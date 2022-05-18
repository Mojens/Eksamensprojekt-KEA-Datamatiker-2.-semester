package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import org.springframework.stereotype.Service;

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


}
