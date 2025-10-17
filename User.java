import java.util.*;

public class User {
    int role;
    String name;
    String password;

    public User(int role, String name, String password) {
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public static User signUp(){
        Scanner pem = new Scanner(System.in);

        System.out.print("---- Sign Up ----");
        System.out.print("\nEnter Role (1 = Organizer, 2 = Student, 3 = Admin): ");
        int role = pem.nextInt(); pem.nextLine();

        System.out.print("Enter Name: ");
        String name = pem.nextLine();

        System.out.print("Enter Password: ");
        String password = pem.nextLine();

        switch (role) {
            case 1:
                return new Admin(role, name, password);
            case 2:
                return new Student(role, name, password);
            case 3:
                return new Admin(role, name, password);
            default:
                System.out.println("Invalid role.");
                return new User(role, name, password);
        }
    }

    public boolean logIn(String name, String password){
        return this.name.equals(name) && this.password.equals(password);
    }
}
