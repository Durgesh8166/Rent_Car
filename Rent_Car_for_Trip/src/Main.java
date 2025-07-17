import java.time.LocalDate;
import java.util.*;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;
    private String category;

    public Car(String carId, String brand, String model, double basePricePerDay, String category) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.category = category;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getCategory() {
        return category;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public double calculatePrice(int rentalDays, boolean insuranceSelected) {
        double insuranceCharge = insuranceSelected ? 200.0 * rentalDays : 0.0;
        return (basePricePerDay * rentalDays) + insuranceCharge;
    }
}

class Customer {
    private String customerId;
    private String name;
    private int rentalCount;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.rentalCount = 0;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void incrementRentalCount() {
        rentalCount++;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;
    private boolean insurance;
    private LocalDate rentDate;
    private LocalDate expectedReturnDate;

    public Rental(Car car, Customer customer, int days, boolean insurance) {
        this.car = car;
        this.customer = customer;
        this.days = days;
        this.insurance = insurance;
        this.rentDate = LocalDate.now();
        this.expectedReturnDate = rentDate.plusDays(days);
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    private double totalRevenue;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
        totalRevenue = 0.0;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId)) {
                return car;
            }
        }
        return null;
    }

    public void rentCar(Car car, Customer customer, int days, boolean insurance) {
        if (car.isAvailable()) {
            car.rent();
            Rental rental = new Rental(car, customer, days, insurance);
            rentals.add(rental);
            customer.incrementRentalCount();

            double price = car.calculatePrice(days, insurance);
            if (customer.getRentalCount() > 3) {
                price *= 0.9; // 10% loyalty discount
            }
            totalRevenue += price;

            System.out.printf("\nTotal Price after discount (if any): $%.2f\n", price);
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar().equals(car)) {
                rentalToRemove = rental;
                break;
            }
        }

        if (rentalToRemove != null) {
            LocalDate today = LocalDate.now();
            if (today.isAfter(rentalToRemove.getExpectedReturnDate())) {
                long lateDays = today.toEpochDay() - rentalToRemove.getExpectedReturnDate().toEpochDay();
                double fine = 100.0 * lateDays;
                System.out.printf("Late return! Fine of $%.2f applied for %d extra days.\n", fine, lateDays);
                totalRevenue += fine;
            }
            rentals.remove(rentalToRemove);
        } else {
            System.out.println("Rental record not found.");
        }
    }

    public void showRevenueReport() {
        System.out.printf("\nTotal Revenue: $%.2f\n", totalRevenue);
    }

    public void showActiveRentals() {
        System.out.println("\n=== Active Rentals ===");
        for (Rental rental : rentals) {
            System.out.println("Car: " + rental.getCar().getBrand() + " " + rental.getCar().getModel() +
                    " | Customer: " + rental.getCustomer().getName() +
                    " | Return by: " + rental.getExpectedReturnDate());
        }
    }

    public void showMostRentedCars() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Rental rental : rentals) {
            String key = rental.getCar().getCarId();
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        String topCarId = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCarId = entry.getKey();
            }
        }

        if (topCarId != null) {
            Car topCar = findCarById(topCarId);
            System.out.println("\nMost Rented Car: " + topCar.getBrand() + " " + topCar.getModel() + " (" + max + " times)");
        } else {
            System.out.println("\nNo rentals yet.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. View Revenue Report");
            System.out.println("4. View Active Rentals");
            System.out.println("5. View Most Rented Car");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    Customer customer = new Customer("CUS" + (customers.size() + 1), name);
                    addCustomer(customer);

                    System.out.println("\nAvailable Cars:");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + ": " + car.getBrand() + " " + car.getModel() + " (" + car.getCategory() + ")");
                        }
                    }

                    System.out.print("\nEnter Car ID: ");
                    String carId = scanner.nextLine();
                    Car car = findCarById(carId);

                    if (car != null && car.isAvailable()) {
                        System.out.print("Enter rental days: ");
                        int days = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Add Insurance for ₹200/day? (Y/N): ");
                        String ins = scanner.nextLine();
                        boolean insurance = ins.equalsIgnoreCase("Y");

                        rentCar(car, customer, days, insurance);
                    } else {
                        System.out.println("Car not available.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Car ID to return: ");
                    String carId = scanner.nextLine();
                    Car car = findCarById(carId);
                    if (car != null && !car.isAvailable()) {
                        returnCar(car);
                        System.out.println("Car returned successfully.");
                    } else {
                        System.out.println("Invalid or already returned.");
                    }
                }
                case 3 -> showRevenueReport();
                case 4 -> showActiveRentals();
                case 5 -> showMostRentedCars();
                case 6 -> {
                    System.out.println("Thank you for using Car Rental System!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();
        system.addCar(new Car("C001", "Toyota", "Camry", 60.0, "Sedan"));
        system.addCar(new Car("C002", "Honda", "Accord", 70.0, "Sedan"));
        system.addCar(new Car("C003", "Mahindra", "Thar", 150.0, "SUV"));
        system.addCar(new Car("C004", "BMW", "X5", 300.0, "Luxury"));
        system.addCar(new Car("C005", "Maruti", "Swift", 45.0, "Economy"));

        system.menu();
    }
}
