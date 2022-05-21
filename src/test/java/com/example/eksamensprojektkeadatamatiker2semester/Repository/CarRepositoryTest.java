package com.example.eksamensprojektkeadatamatiker2semester.Repository;
import com.example.eksamensprojektkeadatamatiker2semester.Model.Car;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    CarRepository carRepository = new CarRepository();


    @Test
    void showAllCars() {

        List<Car> carList = carRepository.showAllCars();

        for (int i = 0; i < carList.size(); i++) {
            assertNotNull(carList.get(i));
            assertNotNull(carList.get(i).getBrand());
        }
        assertEquals(14, carList.size());

    }

    @Test
    void showAllAvaibleCars() {

        List<Car> car = carRepository.showAllAvaibleCars();

        for (int i = 0; i < car.size(); i++) {
            assertNotNull(car.get(i));
            assertNotNull(car.get(i).isLeased());
        }
        assertEquals(1, car.size());
    }

    @Test
    void findCarByID() {

        Car car = carRepository.findCarByID(1);

        assertNotNull(car);

        assertNotNull(car.getStelNummer());
    }

    @Test
    void findCarByLast() {

        List<Car> carList = carRepository.findCarByLast();

        var car = carRepository.findCarByLast();

        int lastCar = 0;
        for (int i = 0; i < car.size(); i++) {
            lastCar = car.get(13).getVognNummer();

        }
        Car cars = carRepository.findCarByID(lastCar);
        int lastInList = cars.getVognNummer();

        for (int i = 0; i < carList.size(); i++) {
            assertNotNull(carList.get(i));
            assertEquals(1, lastInList);

        }


    }

    @Test
    void addCar() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda", "Tesla", "Honda", "Toyota", "Audi", "Jeep", "Dodge", "Nissan", "Volvo", "Citroen", "Jaguar"};
        String[] model = {"C", "V", "X", "O", "MO", "XO", "Model", "R", "Speed", "QE", "E"};
        Random generator = new Random();
        String randomIndex = String.valueOf(generator.nextInt(cars.length));
        String ri = String.valueOf(generator.nextInt(model.length));
        double carID = Math.random();
        String stelNummer = "MDK";
        int smallNumber = ThreadLocalRandom.current().nextInt(1, 99 + 1);
        String con = model[Integer.parseInt(ri)] + smallNumber;
        int randomNum = ThreadLocalRandom.current().nextInt(3000, 9900 + 1);
        String concat = stelNummer + randomNum + "DJ";
        Car car = new Car((int) carID, concat, cars[Integer.parseInt(randomIndex)], con, randomNum, 1);
        deleteCarTest();
        assertTrue(carRepository.addCar(car));

    }


    @Test
    void deleteCar() {
        int i;

        List<Car> firstCheck = carRepository.showAllCars();

        for (int s = 0; s < firstCheck.size(); s++) {
            i = firstCheck.get(s).getVognNummer();
            if (carRepository.findCarByID(i).getVognNummer() == 0) {
                i = i + 1;
                firstCheck.get(s).setVognNummer(i);
            } else if (carRepository.findCarByID(i).getVognNummer() > 5) {
                carRepository.deleteCar(i);
                break;
            }
        }

        List<Car> secondCheck = carRepository.showAllCars();
        assertTrue(firstCheck.size() > secondCheck.size());
        addCarTest();
    }

    @Test
    void editCar() {

        Car oldCar = carRepository.findCarByID(2);

        int randomNum = ThreadLocalRandom.current().nextInt(3000, 10000 + 1);

        Car updatedCar = new Car(2, "DFA", "Volvo", "X12", randomNum, 1);

        assertTrue(carRepository.editCar(updatedCar, 2));
        assertNotEquals(oldCar.getPrice(), updatedCar.getPrice());
        assertEquals(oldCar.getVognNummer(), updatedCar.getVognNummer());

    }

    void addCarTest() {
        double carID = Math.random();
        String stelNummer = "MDK201";
        double rand = Math.random();
        String concat = stelNummer + rand;
        Car car = new Car((int) carID, concat, "Passat", "v12", rand, 1);
        assertTrue(carRepository.addCar(car));
    }

    void deleteCarTest() {
        int i;

        List<Car> firstCheck = carRepository.showAllCars();

        for (int s = 0; s < firstCheck.size(); s++) {
            i = firstCheck.get(s).getVognNummer();
            if (carRepository.findCarByID(i).getVognNummer() == 0) {
                i = i + 1;
                firstCheck.get(s).setVognNummer(i);
            } else if (carRepository.findCarByID(i).getVognNummer() > 2) {
                carRepository.deleteCar(i);
                break;
            }
        }

        List<Car> secondCheck = carRepository.showAllCars();
        assertTrue(firstCheck.size() > secondCheck.size());
    }
}