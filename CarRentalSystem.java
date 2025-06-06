
package car.rental.system;

import java.time.LocalDate;
import java.util.*;

// Abstract class for common person properties
abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

// Customer inherits from Person
class Customer extends Person {
    public Customer(String id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}

// Car class
class Car {
    private String carId;
    private String model;
    private boolean isAvailable;

    public Car(String carId, String model) {
        this.carId = carId;
        this.model = model;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }

    public void rent() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }

    @Override
    public String toString() {
        return model + " (ID: " + carId + ") - " + (isAvailable ? "Available" : "Rented");
    }
}

// Rental transaction class
class RentalTransaction {
    private Car car;
    private Customer customer;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public RentalTransaction(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        this.rentalDate = LocalDate.now();
    }

    public void returnCar() {
        this.returnDate = LocalDate.now();
        car.returnCar();
    }

    public String getSummary() {
        return "Transaction: " + customer.getName() + " rented " + car.getModel() + " on " + rentalDate +
               (returnDate != null ? " and returned on " + returnDate : " (Not yet returned)");
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public String getCarId() {
        return car.getCarId();
    }
}

// Rental agency class
class RentalAgency {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<RentalTransaction> transactions = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean rentCar(String customerId, String carId) {
        Car car = findCar(carId);
        Customer customer = findCustomer(customerId);

        if (car != null && car.isAvailable() && customer != null) {
            car.rent();
            transactions.add(new RentalTransaction(car, customer));
            return true;
        }
        return false;
    }

    public boolean returnCar(String carId) {
        for (RentalTransaction t : transactions) {
            if (t.getCarId().equals(carId) && !t.isReturned()) {
                t.returnCar();
                return true;
            }
        }
        return false;
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (RentalTransaction t : transactions) {
                System.out.println(t.getSummary());
            }
        }
    }

    private Car findCar(String id) {
        return cars.stream().filter(c -> c.getCarId().equals(id)).findFirst().orElse(null);
    }

    private Customer findCustomer(String id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}

// Main class
public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        Car car1 = new Car("C001", "Bayerische Motoren Werke AG");
        Car car2 = new Car("C002", "Audi");
        Car car3 = new Car("C003", "Mcclaren");
        agency.addCar(car1);
        agency.addCar(car2);
        agency.addCar(car3);

        Customer cust1 = new Customer("CU1", "Willyce");
        Customer cust2 = new Customer("CU2", "Ojwang");
        agency.addCustomer(cust1);
        agency.addCustomer(cust2);

        // Renting cars
        System.out.println("Renting Cars...");
        agency.rentCar("CU1", "C001");
        agency.rentCar("CU2", "C002");

        // Show transactions
        System.out.println("\nCurrent Transactions:");
        agency.showTransactions();

        // Return one car
        System.out.println("\nReturning Car C001...");
        agency.returnCar("C001");

        // Show updated transactions
        System.out.println("\nUpdated Transactions:");
        agency.showTransactions();
    }
}


