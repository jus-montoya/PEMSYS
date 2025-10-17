import java.util.List;
import java.util.Scanner;

public class Organizer extends User {
    private String organization; 
    private Scheduling sched = new Scheduling();

    public Organizer(int role, String name, String password, String organization) {
        super(role, name, password);
        this.organization = organization;
    }


    public String getOrganization() {
        return organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void createEvent(List<Scheduling> allSchedules) {
        System.out.println("Organization: " + organization);
        sched.createEvent(allSchedules);
    }

    public void editEvent() {
        sched.editEvent();
    }

    public void viewAllSchedules(List<Scheduling> allSchedules) {
        sched.displayAllSchedules(allSchedules);
    }
}

