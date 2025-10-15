import java.util.*;

public class PEMSYS_Main {
    static List<Scheduling> allSchedules = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static Scanner pem = new Scanner(System.in);

    public static void main(String[] args) {
        PEMSYS_Main app = new PEMSYS_Main();
        app.menu();
    }

    void menu() {
        while (true) {
            System.out.println("\n--- PEMSYS Main Menu ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = pem.nextLine();

            switch (choice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Exiting PEMSYS. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void signUp() {
        User newUser = User.signUp();
        users.add(newUser);
        System.out.println("Sign-up successful! You can now log in.");
    }

    void login() {
        System.out.print("Enter Name: ");
        String name = pem.nextLine();
        System.out.print("Enter Password: ");
        String password = pem.nextLine();

        for (User u : users) {
            if (u.logIn(name, password)) {
                System.out.println("Login successful!");
                redirectUser(u);
                return;
            }
        }
        System.out.println("Login failed. Please try again.");
    }

    void redirectUser(User u) {
        if (u instanceof Student) {
            studentMenu((Student) u);
        } else if (u instanceof Admin) {
            adminMenu((Admin) u);
        } else {
            System.out.println("Unknown role. Access denied.");
        }
    }

    void studentMenu(Student s) {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Set Schedule");
            System.out.println("2. Update Schedule");
            System.out.println("3. View My Schedule");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = pem.nextLine();

            switch (choice) {
                case "1":
                    s.setSchedule(allSchedules);
                    break;
                case "2":
                    s.updateSchedule();
                    break;
                case "3":
                    s.viewSchedule();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void adminMenu(Admin a) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Create Event");
            System.out.println("2. Edit Event");
            System.out.println("3. View All Schedules");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = pem.nextLine();

            switch (choice) {
                case "1":
                    a.createEvent(allSchedules);
                    break;
                case "2":
                    a.editEvent();
                    break;
                case "3":
                    a.sched.displayAllSchedules(allSchedules);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
