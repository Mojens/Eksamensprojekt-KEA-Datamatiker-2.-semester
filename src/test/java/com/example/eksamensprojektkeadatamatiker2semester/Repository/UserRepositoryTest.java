package com.example.eksamensprojektkeadatamatiker2semester.Repository;

import com.example.eksamensprojektkeadatamatiker2semester.Model.Employee;
import com.example.eksamensprojektkeadatamatiker2semester.Model.User;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    EmployeeRepository employeeRepository = new EmployeeRepository();

    @Test
    void createNewUser() {

        String[] userNames = {"Volvo", "BMW", "Ford", "test", "random", "user", "Mazda", "Tesla", "Honda", "Toyota",
                "Audi", "Jeep", "Dodge", "Nissan", "Volvo", "Citroen", "Jaguar", "ad", "pokos", "malt"};
        Random generator = new Random();
        String randomIndex = String.valueOf(generator.nextInt(userNames.length));
        int smallNumber = ThreadLocalRandom.current().nextInt(1, 999 + 1);
        String newUserName = userNames[Integer.parseInt(randomIndex)] + smallNumber;
        var userRepository = new UserRepository();

        User user = (new User("newCode", newUserName, 4, 1));

        assertTrue(userRepository.createNewUser(user));

    }

    @Test
    void findUserByUserName() {

        User user = userRepository.findUserByUserName("admin");

        assertNotNull(user);

        assertNotNull(user.getUsername());

    }

    @Test
    void findUserByEmployeeID() {

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
    void changePasswordOfUser() {

        int smallNumber = ThreadLocalRandom.current().nextInt(1, 999999 + 1);
        String userName = "leje";

        User userBefore = userRepository.findUserByUserName(userName);

        var user = new UserRepository();

        user.changePassword(userName, String.valueOf(smallNumber), userBefore);
        User userAfter = userRepository.findUserByUserName(userName);

        assertNotEquals(userBefore.getPassword(), userAfter.getPassword());
        assertEquals(userAfter.getUsername(), userBefore.getUsername());
    }

    @Test
    void changeStatusForUser_ByUserID() {

        User userBefore = userRepository.findUserByID(2);
        var user = new UserRepository();

        user.ChangeStatusUserByID(2);


        User userAfter = userRepository.findUserByID(2);

        assertNotEquals(userBefore.getStatus(), userAfter.getStatus());
        assertEquals(userBefore.getUserID(), userAfter.getUserID());
        user.changeStatusForUserByIDToOne(2);


    }

    @Test
    void changeStatusForUserByIDToOne() {

        User userBefore = userRepository.findUserByID(2);
        var user = new UserRepository();
        user.ChangeStatusUserByID(2);

        User userAfter = userRepository.findUserByID(2);

        assertNotEquals(userBefore.getStatus(), userAfter.getStatus());
        assertEquals(userBefore.getUserID(), userAfter.getUserID());
        user.changeStatusForUserByIDToOne(2);

    }
}