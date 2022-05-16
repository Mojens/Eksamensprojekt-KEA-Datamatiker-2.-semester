package com.example.eksamensprojektkeadatamatiker2semester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class EksamensprojektKeaDatamatiker2SemesterApplication {

  public static void main(String[] args) {
    SpringApplication.run(EksamensprojektKeaDatamatiker2SemesterApplication.class, args);
  }

}
