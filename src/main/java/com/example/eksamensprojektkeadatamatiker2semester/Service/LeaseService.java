package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;

@Service
public class LeaseService {

    LeaseRepository leaseRepository;

    public LeaseService(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    public LocalDate convertToLocalDate(String startDate) {
        // String format = 1999-22-03
        Scanner scanner = new Scanner(startDate);
        scanner.useDelimiter("-");

        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate;
    }
}
