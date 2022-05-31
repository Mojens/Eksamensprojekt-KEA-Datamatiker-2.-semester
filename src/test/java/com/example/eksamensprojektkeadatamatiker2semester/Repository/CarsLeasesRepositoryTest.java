package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.CarsLeases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarsLeasesRepositoryTest {

    CarsLeasesRepository carsLeasesRepository = new CarsLeasesRepository();
    CarRepository carRepository = new CarRepository();
    LeaseRepository leaseRepository = new LeaseRepository();

    @Test
    void addCarsLease() {

        var car = carRepository.findCarByID(3);
        var lease = leaseRepository.findLeaseByID(3);

        CarsLeases carsLeases = new CarsLeases(car.getVognNummer(),lease.getLeaseID());

        carsLeasesRepository.addCarsLease(carsLeases);

        assertNotNull(carsLeases);


    }

    @Test
    void isLeased() {
        CarsLeases carBefore = carsLeasesRepository.findCarsLeasesByLeaseID(2);
        var carLeases = new CarsLeasesRepository();

        carLeases.isNotLeased(2);


        CarsLeases carAfter = carsLeasesRepository.findCarsLeasesByLeaseID(2);


        assertEquals(carBefore.getLeaseID(), carAfter.getLeaseID());
        carLeases.isLeased(2);
    }

    @Test
    void isNotLeased() {

        CarsLeases carBefore = carsLeasesRepository.findCarsLeasesByLeaseID(2);
        var carLeases = new CarsLeasesRepository();

        carLeases.isNotLeased(2);


        CarsLeases carAfter = carsLeasesRepository.findCarsLeasesByLeaseID(2);


        assertEquals(carBefore.getLeaseID(), carAfter.getLeaseID());

    }

    @Test
    void findCarsLeasesByLeaseID() {
        CarsLeases car = carsLeasesRepository.findCarsLeasesByLeaseID(2);

        assertNotNull(car);
        assertEquals(2,car.getId());

    }
}