package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    EmployeeRepository employeeRepository = new EmployeeRepository();

    @Test
    void createNewUser() {


    }

    @Test
    void findUserByUserName() {

        User user = userRepository.findUserByUserName("admin");

        assertNotNull(user);

        assertNotNull(user.getUsername());

    }

    @Test
    void findUserByEmployee() {

        Employee employee = employeeRepository.findEmployeeByUserID(1);

        User user = userRepository.findUserByEmployee(employee);

        assertNotNull(user);

        assertNotNull(user.getUsername());

    }

    /* Bliver ikke brugt, SLET hvis den ikke bliver brugt inden vi aflevere
    @Test
    void findUserByID() {
    }*/

    /* Bliver ikke brugt, SLET hvis den ikke bliver brugt inden vi aflevere
    @Test
    void showAllUsers() {
    }*/

    @Test
    void changePassword() {
    }

    @Test
    void changeStatusUserByID() {
    }
}