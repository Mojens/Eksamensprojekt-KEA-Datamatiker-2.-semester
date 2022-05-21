package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardRepositoryTest {

    DashboardRepository dashboardRepository = new DashboardRepository();

    @Test
    void addLeasedCarsToList() {
        List<Car> carList = dashboardRepository.addLeasedCarsToList();

        for (int i = 0; i < carList.size(); i++) {
            assertNotNull(carList.get(i));
            assertNotNull(carList.get(i).getBrand());
        }
        assertEquals(13, carList.size());

    }

    }
