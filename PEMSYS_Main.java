import java.util.*;

public class PEMSYS_Main {
    static List<User> users = new ArrayList<>();
    static List<Scheduling> allSchedules = new ArrayList<>();
    static Scanner pem = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- PEM Scheduling System ----");
            System.out.println("[1] Sign Up");
            System.out.println("[2] Log In");
            System.out.println("[3] Exit");
            System.out.print("Choose option: ");
            int choice = pem.nextInt();
            pem.nextLine();

            switch (choice) {
                case 1 -> signUp();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void signUp() {
        User newUser = User.signUp();
        users.add(newUser);
        System.out.println("Account created successfully!");
    }

    static void login() {
        System.out.print("Enter Name: ");
        String name = pem.nextLine();
        System.out.print("Enter Password: ");
        String password = pem.nextLine();

        for (User user : users) {
            if (user.logIn(name, password)) {
                System.out.println("\nLogin successful! Welcome " + user.name);
                menu(user);
                return;
            }
        }
        System.out.println("Invalid name or password!");
    }

    static void menu(User user) {
        if (user instanceof Admin admin) {
            while (true) {
                System.out.println("\n---- Admin Menu ----");
                System.out.println("[1] Create Event");
                System.out.println("[2] Edit Event");
                System.out.println("[3] View All Events");
                System.out.println("[4] Logout");
                System.out.print("Choose: ");
                int choice = pem.nextInt();
                pem.nextLine();

                switch (choice) {
                    case 1 -> admin.createEvent(allSchedules);
                    case 2 -> admin.editEvent();
                    case 3 -> new Scheduling().displayAllSchedules(allSchedules);
                    case 4 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } else if (user instanceof Student student) {
            while (true) {
                System.out.println("\n---- Student Menu ----");
                System.out.println("[1] Set Schedule");
                System.out.println("[2] Update Schedule");
                System.out.println("[3] View Schedule");
                System.out.println("[4] View All Events");
                System.out.println("[5] Logout");
                System.out.print("Choose: ");
                int choice = pem.nextInt();
                pem.nextLine();

                switch (choice) {
                    case 1 -> student.setSchedule(allSchedules);
                    case 2 -> student.updateSchedule();
                    case 3 -> student.viewSchedule();
                    case 4 -> new Scheduling().displayAllSchedules(allSchedules);
                    case 5 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Unknown user role.");
        }
    }
}

