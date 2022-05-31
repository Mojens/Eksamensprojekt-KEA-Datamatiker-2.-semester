package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.DamageReport;
import org.junit.jupiter.api.Test;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DamageReportRepositoryTest {

    DamageReportRepository damageReportRepository = new DamageReportRepository();
    LeaseRepository leaseRepository = new LeaseRepository();
    CarRepository carRepository = new CarRepository();

    @Test
    void showAllDamageReports() {

        List<DamageReport> damageReportList = damageReportRepository.showAllDamageReports();

        for (int i = 0; i < damageReportList.size(); i++) {
            assertNotNull(damageReportList.get(i));

        }
        assertEquals(6, damageReportList.size());
    }

    @Test
    void checkIfExists() {

        var damageReport1 = damageReportRepository.findReportByID(1);
        var damageReport = damageReportRepository.findReportByID(1000);

        var lease1 = leaseRepository.findLeaseByID(1);
        var lease = leaseRepository.findLeaseByID(2);

        var car1 = carRepository.findCarByID(1);
        var car = carRepository.findCarByID(2);


        assertEquals(damageReport1.getVognNummer(),car1.getVognNummer());
        assertEquals(damageReport1.getLeaseID(),lease1.getLeaseID());
        assertNotEquals(damageReport1.getVognNummer(),car.getVognNummer());
        assertNotEquals(damageReport.getLeaseID(),lease.getLeaseID());

        assertNotEquals(1000,damageReport.getDamageReportID());



    }

    @Test
    void findReportByID() {

        var damageReport1 = damageReportRepository.findReportByID(2);

        var damageReport = damageReportRepository.findReportByID(1);

        assertNotNull(damageReport);
        assertNotSame(damageReport1, damageReport);
        assertEquals(1, damageReport.getDamageReportID());

    }

    @Test
    void findReportByLast() {

        List<DamageReport> damageReportList = damageReportRepository.findReportByLast();
        DamageReport damageReport = damageReportRepository.findReportByID(5);
        int lastInList = damageReport.getDamageReportID();

        for (int i = 0; i < damageReportList.size(); i++) {
            assertNotNull(damageReportList.get(i));
            assertEquals(2, damageReportList.get(lastInList-1).getDamageReportID());

        }

    }

    @Test
    void addDamageReport() {

        var car = carRepository.findCarByLast();
        var lease = leaseRepository.findLeaseByLast();

        int lastCar = 0;
        for (int i = 0; i < car.size(); i++) {
             lastCar = car.get(0).getVognNummer();

        }
        int lastLease = 0;
        for (int i = 0; i < lease.size(); i++) {
            lastLease = lease.get(0).getLeaseID();

        }

        DamageReport damageReport = new DamageReport(lastLease,lastCar,2);

        System.out.println(damageReport.getLeaseID());
        assertTrue(damageReportRepository.addDamageReport(damageReport));
        deleteDamageReportTest();


    }

    @Test
    void deleteDamageReport() {
        int i;

        List<DamageReport> firstCheck = damageReportRepository.showAllDamageReports();

        for (int s = 0; s < firstCheck.size(); s++) {
            i = firstCheck.get(s).getDamageReportID();
            if (damageReportRepository.findReportByID(i).getDamageReportID() == 0) {
                i = i + 1;
                firstCheck.get(s).setDamageReportID(i);
            } else if (damageReportRepository.findReportByID(i).getDamageReportID() > 5) {
                damageReportRepository.deleteDamageReport(i);
                break;
            }
        }

        List<DamageReport> secondCheck = damageReportRepository.showAllDamageReports();
        assertTrue(firstCheck.size() > secondCheck.size());
        addDamageReportTest();

    }

    @Test
    void editDamageReport() {

        var oldDamageReport = damageReportRepository.findReportByID(2);

        DamageReport updatedDamageReport = new DamageReport(2,1,3,2);

        assertTrue(damageReportRepository.editDamageReport(updatedDamageReport,2));
        assertNotEquals(oldDamageReport.getLeaseID(), updatedDamageReport.getLeaseID());
        assertEquals(oldDamageReport.getDamageReportID(), updatedDamageReport.getDamageReportID());
    }


    void deleteDamageReportTest() {
        int i;

        List<DamageReport> firstCheck = damageReportRepository.showAllDamageReports();

        for (int s = 0; s < firstCheck.size(); s++) {
            i = firstCheck.get(s).getDamageReportID();
            if (damageReportRepository.findReportByID(i).getDamageReportID() == 0) {
                i = i + 1;
                firstCheck.get(s).setDamageReportID(i);
            } else if (damageReportRepository.findReportByID(i).getDamageReportID() > 5) {
                damageReportRepository.deleteDamageReport(i);
                break;
            }
        }

        List<DamageReport> secondCheck = damageReportRepository.showAllDamageReports();
        assertTrue(firstCheck.size() > secondCheck.size());

    }

    void addDamageReportTest(){

        var car = carRepository.findCarByLast();
        var lease = leaseRepository.findLeaseByLast();

        int lastCar = 0;
        for (int i = 0; i < car.size(); i++) {
            lastCar = car.get(0).getVognNummer();

        }
        int lastLease = 0;
        for (int i = 0; i < lease.size(); i++) {
            lastLease = lease.get(0).getLeaseID();

        }

        DamageReport damageReport = new DamageReport(lastLease,lastCar,2);

        assertTrue(damageReportRepository.addDamageReport(damageReport));

    }
}