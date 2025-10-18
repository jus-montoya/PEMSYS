import java.util.*;
import java.io.*;

public class PEMSYS_Main {
    static List<Scheduling> allSchedules = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static Scanner pem = new Scanner(System.in);

    public static void main(String[] args) {
        PEMSYS_Main app = new PEMSYS_Main();
        app.loadCSV();
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
                case "1" -> signUp();
                case "2" -> login();
                case "3" -> {
                    saveCSV();
                    System.out.println("Exiting PEMSYS. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void signUp() {
        User newUser = User.signUp();
        users.add(newUser);
        saveCSV();
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
        if (u instanceof Student s) {
            studentMenu(s);
        } else if (u instanceof Admin a) {
            adminMenu(a);
        } else if (u instanceof Organizer o) {
            organizerMenu(o);
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
                case "1" -> { s.setSchedule(allSchedules); saveCSV(); }
                case "2" -> { s.updateSchedule(); saveCSV(); }
                case "3" -> s.viewSchedule();
                case "4" -> { saveCSV(); return; }
                default -> System.out.println("Invalid choice. Try again.");
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
                    saveCSV();
                    break;
                case "2":
                    a.editEvent();
                    saveCSV();
                    break;
                case "3":
                    a.sched.displayAllSchedules(allSchedules);
                    break;
                case "4":
                    saveCSV();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void organizerMenu(Organizer o) {
        while (true) {
            System.out.println("\n--- Organizer Menu (" + o.getOrganization() + ") ---");
            System.out.println("1. Create Event");
            System.out.println("2. Edit Event");
            System.out.println("3. View All Schedules");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = pem.nextLine();

            switch (choice) {
                case "1" -> { o.createEvent(allSchedules); saveCSV(); }
                case "2" -> { o.editEvent(); saveCSV(); }
                case "3" -> o.viewAllSchedules(allSchedules);
                case "4" -> { saveCSV(); return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void saveCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("PEMSYS_Data.csv"))) {
            pw.println("USERS");
            for (User u : users) {
                if (u instanceof Organizer o)
                    pw.println(u.getRole() + "," + u.getName() + "," + u.getPassword() + "," + o.getOrganization());
                else
                    pw.println(u.getRole() + "," + u.getName() + "," + u.getPassword());
            }
            pw.println("SCHEDULES");
            for (Scheduling s : allSchedules) {
                pw.println(s.eventType + "," + s.date + "," + s.time + "," + s.venue);
            }
            System.out.println("Data saved to PEMSYS_Data.csv");
        } catch (IOException e) {
            System.out.println("Error saving CSV: " + e.getMessage());
        }
    }

    void loadCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("PEMSYS_Data.csv"))) {
            String line;
            boolean userSection = false, scheduleSection = false;

            while ((line = br.readLine()) != null) {
                if (line.equals("USERS")) {
                    userSection = true;
                    scheduleSection = false;
                    continue;
                } else if (line.equals("SCHEDULES")) {
                    userSection = false;
                    scheduleSection = true;
                    continue;
                }

                if (userSection && !line.isEmpty()) {
                    String[] parts = line.split(",");
                    int role = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String password = parts.length > 2 ? parts[2] : "";
                    if (role == 1) {
                        String org = parts.length > 3 ? parts[3] : "Unknown Organization";
                        users.add(new Organizer(role, name, password, org));
                    } else if (role == 2)
                        users.add(new Student(role, name, password));
                    else if (role == 3)
                        users.add(new Admin(role, name, password));
                } else if (scheduleSection && !line.isEmpty()) {
                    String[] parts = line.split(",");
                    Scheduling s = new Scheduling();
                    s.eventType = parts[0];
                    s.date = parts[1];
                    s.time = parts[2];
                    s.venue = parts.length > 3 ? parts[3] : "";
                    allSchedules.add(s);
                }
            }
            System.out.println("Data loaded from PEMSYS_Data.csv");
        } catch (IOException e) {
            System.out.println("No CSV data found, starting fresh.");
        }
    }
}
