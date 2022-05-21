package com.example.eksamensprojektkeadatamatiker2semester.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class LeaseTest {



    @Test
    void compareNowAndEndDate() {

        LocalDate today = LocalDate.now();
        LocalDate earlierThanToday = LocalDate.parse("2022-01-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate laterThanToday = LocalDate.parse("2022-05-25", DateTimeFormatter.ISO_LOCAL_DATE);
        Lease lease = new Lease();

        assertEquals(1,lease.compareNowAndEndDate(today));
        assertEquals(1,lease.compareNowAndEndDate(earlierThanToday));
        assertEquals(0,lease.compareNowAndEndDate(laterThanToday));


    }

    @Test
    void subtractDates() {

        LocalDate startDate = LocalDate.parse("2022-01-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2022-05-21", DateTimeFormatter.ISO_LOCAL_DATE);
        long month = ChronoUnit.MONTHS.between(startDate,endDate);
        long diff = ChronoUnit.DAYS.between(startDate, endDate);

        assertEquals(129,diff);
        assertEquals(month,endDate.compareTo(startDate));

    }
}