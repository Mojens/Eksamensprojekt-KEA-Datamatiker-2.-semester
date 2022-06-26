package com.example.eksamensprojektkeadatamatiker2semester.Service;

import com.example.eksamensprojektkeadatamatiker2semester.Repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;
/* Lavet Af Mohammed */
@Service
public class LeaseService {

  //Laver en instance af leaseRepository i scope
  LeaseRepository leaseRepository;

  //Definer leaseRepository værdi som er i vores scope ind i vores constructor
  public LeaseService(LeaseRepository leaseRepository) {
    this.leaseRepository = leaseRepository;
  }

  //denne metode tager en dato som en string og deler den op, så sætter vi den sammen igen.
  //Dette gør vi så der kan kommunikeres mellem databasens dato og javas dato.
  public LocalDate convertToLocalDate(String startDate) {

    Scanner scanner = new Scanner(startDate);
    scanner.useDelimiter("-");

    int year = scanner.nextInt();
    int month = scanner.nextInt();
    int day = scanner.nextInt();

    LocalDate localDate = LocalDate.of(year, month, day);

    return localDate;
  }

}
