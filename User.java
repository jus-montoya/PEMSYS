import java.util.*;

public class User {
    private int role;
    private String name;
    private String password;

    public User(int role, String name, String password) {
        this.role = role;
        this.name = name;
        this.password = password;
    }


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User signUp() {
        Scanner pem = new Scanner(System.in);

        System.out.print("---- Sign Up ----");
        System.out.print("\nEnter Role (1 = Organizer, 2 = Student, 3 = Admin): ");
        int role = pem.nextInt(); 
        pem.nextLine();

        if (role == 1) {
            System.out.println("\nChoose your organization:");
            System.out.println("1 = Code Geeks");
            System.out.println("2 = GDG");
            System.out.println("3 = MAFIA");
            System.out.println("4 = AWS");
            System.out.println("5 = ACCESS POINT");
            System.out.println("6 = SOC STUDENT COUNCIL");
            System.out.print("Enter choice: ");
            int orgChoice = pem.nextInt();
            pem.nextLine();

            String organization = switch (orgChoice) {
                case 1 -> "Code Geeks";
                case 2 -> "GDG";
                case 3 -> "MAFIA";
                case 4 -> "AWS";
                case 5 -> "ACCESS POINT";
                case 6 -> "SOC STUDENT COUNCIL";
                default -> "Unknown Organization";
            };

            System.out.print("Enter Username: ");
            String name = pem.nextLine();
            System.out.print("Enter Password: ");
            String password = pem.nextLine();

            return new Organizer(role, name, password, organization);
        }

        System.out.print("Enter Name: ");
        String name = pem.nextLine();
        System.out.print("Enter Password: ");
        String password = pem.nextLine();

        return switch (role) {
            case 2 -> new Student(role, name, password);
            case 3 -> new Admin(role, name, password);
            default -> new User(role, name, password);
        };
    }

    public boolean logIn(String name, String password) {
        return this.name.equals(name) && this.password.equals(password);
    }
}
