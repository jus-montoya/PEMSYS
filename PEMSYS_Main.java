import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class PEMSYS_Main {
    static ArrayList<User> users = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PEMSYS_Main system = new PEMSYS_Main();
        system.menu();
    }

    void menu() {
        int choice;
        do {
            System.out.println("\n=== PEMSYS: Academic Organization Platform ===");
            System.out.println("[1] Sign Up");
            System.out.println("[2] Log In");
            System.out.println("[3] Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> signUp();
                case 2 -> logIn();
                case 3 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }

    void signUp() {
        User newUser = User.signUp();
        users.add(newUser);
        saveToCSV("users.csv", newUser.name + "," + newUser.role);
        System.out.println("Sign-up successful! You can now log in.");
    }

    void logIn() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();

        for (User u : users) {
            if (u.name.equals(name)) {
                System.out.println("Login successful!");
                if (u.role == 1 || u.role == 3) { // Organizer or Admin
                    Organizer organizer = new Organizer(u.name, u.role);
                    organizer.createEvent();
                } else if (u.role == 2) { // Student
                    Student student = new Student(u.name, u.role);
                    student.viewSchedule();
                }
                return;
            }
        }
        System.out.println("User not found. Please sign up first.");
    }
    
    void saveToCSV(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + "\n");
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
