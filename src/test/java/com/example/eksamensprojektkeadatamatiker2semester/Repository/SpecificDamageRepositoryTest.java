package com.example.eksamensprojektkeadatamatiker2semester.Repository;

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
        assertEquals(5, specificDamageList.size());
    }

    @Test
    void sumPriceSpecificDamagesByID() {

        SpecificDamage specificDamage1 = specificDamageRepository.sumPriceSpecificDamagesByID(1);

        assertEquals(22752,specificDamage1.getSumTotal());
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

        SpecificDamage specificDamage = new SpecificDamage(11,5000,"Olie lækker, hul skal lappes","default.jpg","Olie læk",1);

        assertTrue(specificDamageRepository.addSpecificDamage(specificDamage));
        specificDamageRepository.deleteSpecificDamage(11);

    }

    @Test
    void deleteSpecificDamage() {

        List<SpecificDamage> firstCheck = specificDamageRepository.showAllSpecificDamages();

        specificDamageRepository.deleteSpecificDamage(10);

        List<SpecificDamage> secondCheck = specificDamageRepository.showAllSpecificDamages();
        assertTrue(firstCheck.size() > secondCheck.size());

        SpecificDamage specificDamage = new SpecificDamage(10,5000,"Olie lækker, hul skal lappes","default.jpg","Olie læk",1);
        specificDamageRepository.addSpecificDamage(specificDamage);


    }

    @Test
    void editSpecificDamage() {

        SpecificDamage updatedSpecificDamageReport = new SpecificDamage(2400,"Ridse i laken","default.jpg","Ridse i laken",2);
        assertTrue(specificDamageRepository.editSpecificDamage(updatedSpecificDamageReport,2));

    }

}