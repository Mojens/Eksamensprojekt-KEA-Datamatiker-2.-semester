package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.CarRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.DamageReportRepository;
import com.example.eksamensprojektkeadatamatiker2semester.Repository.DashboardRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardServiceTest {
    CarRepository carRepository = new CarRepository();
    DashboardService dashboardService = new DashboardService();
    DashboardRepository dashboardRepository = new DashboardRepository();

    @Test
    void howManyisLeased() {

        List<Car> carList = carRepository.showAllCars();
        List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
        List<Car> availableCars = carRepository.showAllAvaibleCars();

        assertEquals( dashboardService.howManyisLeased(leasedCars), carList.size() - availableCars.size());

    }

    @Test
    void totalPriceLeasedCar() {
        List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();
        double totalPrice = 0;
        for (Car c :
                leasedCars) {
            totalPrice = totalPrice + c.getPrice();
        }

        assertEquals(totalPrice,dashboardService.totalPriceLeasedCar(leasedCars));

    }


    @Test
    void percentageStatus() {
        List<Car> carList = carRepository.showAllCars();
        List<Car> leasedCars = dashboardRepository.addLeasedCarsToList();



        int carCounter = 0;
        for (Car allCar : carList) {
            if (allCar.getModel().equalsIgnoreCase(allCar.getModel().toLowerCase())) {
                if (allCar.getBrand().equalsIgnoreCase(allCar.getBrand().toLowerCase())) {
                    carCounter = carCounter + 1;
                }
            }
        }
        int countLeased = 0;
        for (Car leasedCar : leasedCars) {
            if (leasedCar.getModel().equalsIgnoreCase(leasedCar.getModel().toLowerCase())) {
                if (leasedCar.getBrand().equalsIgnoreCase(leasedCar.getBrand().toLowerCase())) {
                    countLeased = countLeased + 1;
                }
            }
        }

        if (countLeased * 100 / carCounter <=25){
            assertTrue(countLeased * 100 / carCounter <=25);
            assertFalse(countLeased * 100 / carCounter >=25);
        }  else if (countLeased * 100 / carCounter > 25 && countLeased * 100 / carCounter  <= 50){
            assertTrue(countLeased * 100 / carCounter > 25 && countLeased * 100 / carCounter  <= 50);
            assertFalse(countLeased * 100 / carCounter < 25 && countLeased * 100 / carCounter  >= 50);
        } else if (countLeased * 100 / carCounter > 50 && countLeased * 100 / carCounter  <= 75){
            assertTrue(countLeased * 100 / carCounter > 50 && countLeased * 100 / carCounter  <= 75);
            assertFalse(countLeased * 100 / carCounter < 50 && countLeased * 100 / carCounter  >= 75);
        }else if (countLeased * 100 / carCounter > 75 && countLeased * 100 / carCounter  <= 100){
            assertTrue(countLeased * 100 / carCounter > 75 && countLeased * 100 / carCounter  <= 100);
            assertFalse(countLeased * 100 / carCounter < 75 && countLeased * 100 / carCounter  >= 100);
        }
    }

    @Test
    void convertLocalToDanish() {

        String currentMonth = dashboardService.convertLocalToDanish(LocalDate.now().getMonth());

        assertEquals("Juni", currentMonth);
        assertNotEquals("June",currentMonth);
    }


}