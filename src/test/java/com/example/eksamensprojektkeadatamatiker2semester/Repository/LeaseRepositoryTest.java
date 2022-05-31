package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Lease;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class LeaseRepositoryTest {
    LeaseRepository leaseRepository = new LeaseRepository();

    @Test
    void showAllLeases() {

        List<Lease> leaseList = leaseRepository.showAllLeases();

        for (int i = 0; i < leaseList.size(); i++) {
            assertNotNull(leaseList.get(i));
            assertNotNull(leaseList.get(i).getFirstName());
        }
        assertEquals(7, leaseList.size());

    }


    @Test
    void showLeases() {
        Lease leases = leaseRepository.showLeases();

        assertEquals(27,leases.getLeaseID());
    }

    @Test
    void findLeaseByID() {

        Lease lease = leaseRepository.findLeaseByID(1);

        assertNotNull(lease);

        assertNotNull(lease.getFirstName());
    }

    @Test
    void findLeaseByIDAsList() {
        Lease lease = leaseRepository.findLeaseByID(1);

        assertEquals(1, lease.getLeaseID());
        assertNotEquals(2,lease.getLeaseID());

    }

    @Test
    void addLease() {

        String[] firstName = {"Hasan", "Karl", "Hardy", "test", "random", "user", "Cay", "Lonne", "Ibrahim", "Malthe",
                "Sofie", "Jeppe", "Dodger", "Nisse", "John", "Lasse", "Jakob", "Ann", "Pokos", "malt"};
        String[] lastNames = {"Hansen", "Karlsen", "Karlsson", "Holm", "Igild", "Christensen", "Artelldo", "Hason", "Kurt Erik", "Adel",
                "Sommer", "Troelsen", "Rasmussen", "Adel", "Lue", "Olesen", "Winton", "Zak", "Frederiksen", "Salmonsen"};
        Random generator = new Random();
        String randomIndex = String.valueOf(generator.nextInt(firstName.length));
        String ri = String.valueOf(generator.nextInt(lastNames.length));

        int day = ThreadLocalRandom.current().nextInt(1, 30 );
        int month = ThreadLocalRandom.current().nextInt(1, 12 );

        LocalDate startDate = LocalDate.of(2022,1,10);

        LocalDate endDate = LocalDate.of(2022,month,day);

        Lease lease = new Lease(firstName[Integer.parseInt(randomIndex)],lastNames[Integer.parseInt(ri)], 2,startDate,endDate);

        assertTrue(leaseRepository.addLease(lease));


    }

    @Test
    void findLeaseByLast() {

        List<Lease> leaseList = leaseRepository.findLeaseByLast();
        Lease lease = leaseRepository.findLeaseByID(3);
        int lastInList = lease.getLeaseID();

        for (int i = 0; i < leaseList.size(); i++) {
            assertNotNull(leaseList.get(i));
            assertEquals(25, leaseList.get(lastInList-1).getLeaseID());

        }
    }

    @Test
    void editLease() {
        Lease oldLease = leaseRepository.findLeaseByID(2);

        int day = ThreadLocalRandom.current().nextInt(1, 30 );
        int month = ThreadLocalRandom.current().nextInt(1, 12 );

        LocalDate startDate = LocalDate.of(2022,1,10);

        LocalDate endDate = LocalDate.of(2022,month,day);

        Lease updatedLease = new Lease(2,"Thies", "Hansen", 1,startDate,endDate,1);

        assertTrue(leaseRepository.editLease(updatedLease,2));
        assertNotEquals(oldLease.getEndDate(), updatedLease.getEndDate());
        assertEquals(oldLease.getLeaseID(), updatedLease.getLeaseID());

    }

    @Test
    void changeStatusLeaseByID() {


        Lease leaseBefore = leaseRepository.findLeaseByID(2);
        var lease = new LeaseRepository();

        lease.ChangeStatusLeaseByID(2);


        Lease leaseAfter = leaseRepository.findLeaseByID(2);

        assertNotEquals(leaseBefore.getStatus(), leaseAfter.getStatus());
        assertEquals(leaseBefore.getUserID(), leaseAfter.getUserID());
        lease.changeStatusLeaseByIDToOne(2);

    }

    @Test
    void changeStatusLeaseByIDToOne() {

        Lease leaseBefore = leaseRepository.findLeaseByID(2);
        var lease = new LeaseRepository();

        lease.ChangeStatusLeaseByID(2);


        Lease leaseAfter = leaseRepository.findLeaseByID(2);

        assertNotEquals(leaseBefore.getStatus(), leaseAfter.getStatus());
        assertEquals(leaseBefore.getUserID(), leaseAfter.getUserID());
        lease.changeStatusLeaseByIDToOne(2);

    }
}