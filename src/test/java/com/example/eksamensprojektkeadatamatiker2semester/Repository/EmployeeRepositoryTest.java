package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    EmployeeRepository employeeRepository = new EmployeeRepository();
    UserRepository userRepository = new UserRepository();

    @Test
    void showAllEmployees() {

        List<Employee> employeeList = employeeRepository.showAllEmployees();

        for (int i = 0; i < employeeList.size(); i++) {
            assertNotNull(employeeList.get(i));
            assertNotNull(employeeList.get(i).getFirstName());
        }
        assertEquals(10, employeeList.size());
    }

    @Test
    void findEmployeeByUserID() {
        User user = userRepository.findUserByID(1);

        Employee employee = employeeRepository.findEmployeeByUserID(user.getUserID());

        assertEquals(1,employee.getUserID());
        assertNotEquals(2,employee.getUserID());

    }

    @Test
    void changeStatusEmployeeByIDToZero() {
        User user = userRepository.findUserByID(2);

        Employee employeeBefore = employeeRepository.findEmployeeByUserID(user.getUserID());
        var employee = new EmployeeRepository();

        employee.ChangeStatusEmployeeByID(2);


        Employee employeeAfter = employeeRepository.findEmployeeByUserID(2);

        assertNotEquals(employeeBefore.getStatus(), employeeAfter.getStatus());
        assertEquals(employeeBefore.getUserID(), employeeAfter.getUserID());
        employee.changeStatusEmployeeByIDToOne(2);
    }

    @Test
    void changeStatusEmployeeByIDToOne(){

        User user = userRepository.findUserByID(2);

        Employee employeeBefore = employeeRepository.findEmployeeByUserID(user.getUserID());
        var employee = new EmployeeRepository();

        employee.ChangeStatusEmployeeByID(2);


        Employee employeeAfter = employeeRepository.findEmployeeByUserID(2);

        assertNotEquals(employeeBefore.getStatus(), employeeAfter.getStatus());
        assertEquals(employeeBefore.getUserID(), employeeAfter.getUserID());
        employee.changeStatusEmployeeByIDToOne(2);
    }

    @Test
    void addNewEmployee() {

        String[] firstName = {"Hasan", "Karl", "Hardy", "test", "random", "user", "Cay", "Lonne", "Ibrahim", "Malthe",
                "Sofie", "Jeppe", "Dodger", "Nisse", "John", "Lasse", "Jakob", "Ann", "Pokos", "malt"};
        String[] lastNames = {"Hansen", "Karlsen", "Karlsson", "Holm", "Igild", "Christensen", "Artelldo", "Hason", "Kurt Erik", "Adel",
                "Sommer", "Troelsen", "Rasmussen", "Adel", "Lue", "Olesen", "Winton", "Zak", "Frederiksen", "Salmonsen"};
        Random generator = new Random();
        String randomIndex = String.valueOf(generator.nextInt(firstName.length));
        int smallNumber = ThreadLocalRandom.current().nextInt(1, 4 );
        int phoneNumber = ThreadLocalRandom.current().nextInt(20000000, 99999999 );
        String phoneCon = "+45"+phoneNumber;
        String newEmail = firstName[Integer.parseInt(randomIndex)] + smallNumber + "@bilabonnement.dk";
        var employeeRepository = new EmployeeRepository();

        Employee employee = (new Employee(firstName[Integer.parseInt(randomIndex)], lastNames[Integer.parseInt(randomIndex)],
                phoneCon,newEmail,smallNumber));

        assertTrue(employeeRepository.addNewEmployee(employee));
    }
}