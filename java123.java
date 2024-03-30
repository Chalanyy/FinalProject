import java.util.ArrayList;
import java.util.Scanner;

class Tester {
    private String idCardNumber;
    private String name;
    private int yearOfBirth;
    private double height;
    private double weight;

    // Constructor
    public Tester(String idCardNumber, String name, int yearOfBirth, double height, double weight) {
        this.idCardNumber = idCardNumber;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.height = height;
        this.weight = weight;
    }

    // Getters and setters for properties
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Display information
    public void display() {
        System.out.println("ID Card Number: " + idCardNumber);
        System.out.println("Name: " + name);
        System.out.println("Year of Birth: " + yearOfBirth);
        System.out.println("Height: " + height + " centimeters");
        System.out.println("Weight: " + weight + " kilograms");
    }

    // Calculate age based on year of birth and current year
    public int calculateAge() {
        int currentYear = java.time.Year.now().getValue(); // Get current year
        return currentYear - yearOfBirth;
    }
}

class Bmi extends Tester {
    // Constructor
    public Bmi(String idCardNumber, String name, int yearOfBirth, double height, double weight) {
        super(idCardNumber, name, yearOfBirth, height, weight);
    }

    // Calculate BMI
    public double calculateBmi() {
        double heightInMeters = getHeight() / 100.0; // Convert height to meters
        return getWeight() / (heightInMeters * heightInMeters);
    }

    // Display BMI information with category
    @Override
    public void display() {
        super.display();
        double bmiValue = calculateBmi();
        System.out.printf("BMI: %.2f\n", bmiValue);

        System.out.print("BMI Category: ");
        if (bmiValue < 16) {
            System.out.println("Severe undernourishment");
        } else if (bmiValue >= 16 && bmiValue <= 16.9) {
            System.out.println("Medium undernourishment");
        } else if (bmiValue >= 17 && bmiValue <= 18.4) {
            System.out.println("Slight undernourishment");
        } else if (bmiValue >= 18.5 && bmiValue <= 24.9) {
            System.out.println("Normal nutrition state");
        } else if (bmiValue >= 25 && bmiValue <= 29.9) {
            System.out.println("Overweight");
        } else if (bmiValue >= 30 && bmiValue <= 39.9) {
            System.out.println("Obesity");
        } else {
            System.out.println("Pathological obesity");
        }
    }
}

public class java123 {
    private static ArrayList<Tester> userList = new ArrayList<>();

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n------ BMI Application Menu ------");
            System.out.println("1. Create a record");
            System.out.println("2. Show BMI data for all users");
            System.out.println("3. Show BMI data for selected user");
            System.out.println("4. Delete all");
            System.out.println("5. Exit application");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createUser();         
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    viewBmiForSelectedUser();
                    break;
                case 4:
                    deleteAllUsers();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (choice != 5);

        scanner.close();}
    

    private static void createUser() {
        Scanner scanner = new Scanner(System.in);

            // Check if the maximum limit has been reached
        if (userList.size() >= 5) {
            System.out.println("Maximum number of users (5) reached. Cannot add more users.");
            return; // Exit the method
        }

        System.out.println("\n------ Create User ------");

        System.out.print("Enter ID card number: ");
        String idCardNumber = scanner.next();

        System.out.print("Enter name: ");
        String name = scanner.next();

        System.out.print("Enter year of birth: ");
        int yearOfBirth = scanner.nextInt();

        System.out.print("Enter height in centimeters: ");
        double height = scanner.nextDouble();

        System.out.print("Enter weight in kilograms: ");
        double weight = scanner.nextDouble();

        Bmi newUser = new Bmi(idCardNumber, name, yearOfBirth, height, weight);
        userList.add(newUser);

        System.out.println("User created successfully!");
    }

    private static void viewUsers() {
        System.out.println("\n------ View Users ------");
        if (userList.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (Tester user : userList) {
                user.display();
                System.out.println("Age: " + user.calculateAge() + " years"); // Display age
                System.out.println("-------------------------------");
            }
        }
    }

    private static void viewBmiForSelectedUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the user ID card number: ");
        String idCardNumber = scanner.next();

        boolean found = false;
        for (Tester user : userList) {
            if (user.getIdCardNumber().equals(idCardNumber)) {
                user.display();
                System.out.println("Age: " + user.calculateAge() + " years"); // Display age
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("User not found with ID card number: " + idCardNumber);
        }
    }

    private static void deleteAllUsers() {
        userList.clear();
        System.out.println("All users deleted successfully!");
    }
}

