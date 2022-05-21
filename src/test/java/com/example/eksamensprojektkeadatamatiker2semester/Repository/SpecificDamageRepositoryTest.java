package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import com.example.eksamensprojektkeadatamatiker2semester.Model.SpecificDamage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecificDamageRepositoryTest {

    SpecificDamageRepository specificDamageRepository = new SpecificDamageRepository();

    @Test
    void showAllSpecificDamages() {


        List<SpecificDamage> specificDamageList = specificDamageRepository.showAllSpecificDamages();

        for (int i = 0; i < specificDamageList.size(); i++) {
            assertNotNull(specificDamageList.get(i));
            assertNotNull(specificDamageList.get(i).getDescription());
        }
        assertEquals(3, specificDamageList.size());
    }

    @Test
    void sumPriceSpecificDamagesByID() {
    }

    @Test
    void findSpecificDamageByReportID() {

        List <SpecificDamage> specificDamage = specificDamageRepository.findSpecificDamageByReportID(1);

        assertNotNull(specificDamage);

        for (int i = 0; i<specificDamage.size();i++){
            assertNotNull(specificDamage.get(1));
            assertEquals(1,specificDamage.get(0).getDamageID());
        }


    }

    @Test
    void addSpecificDamage() {
    }

    @Test
    void deleteSpecificDamage() {
    }

    @Test
    void editSpecificDamage() {
    }
}