import java.util.*;

public class Scheduling{
    String eventType, date, time, venue;

    void setSchedule(List<Scheduling> allSchedules){
        Scanner pem = new Scanner(System.in);

        System.out.println("---- Set Schedule ----");

        System.out.print("Enter Title: ");
        this.eventType = pem.nextLine();

        System.out.print("Enter Date: ");
        this.date = pem.nextLine();

        System.out.print("Enter Time: ");
        this.time = pem.nextLine();

         allSchedules.add(this);

        System.out.println("Schedule set successfully!");
    }

    void updateSchedule(){
        Scanner pem = new Scanner(System.in);

        System.out.print("---- Update Schedule ----");

        System.out.print("Current Event Type: " + eventType + "\nEnter new Event Type (or press Enter to keep): ");
        String newEventType = pem.nextLine();
        if (!newEventType.isEmpty()) {
            eventType = newEventType;
        }

        System.out.print("Current Date: " + date + "\nEnter new Date (or press Enter to keep): ");
        String newDate = pem.nextLine();
        if (!newDate.isEmpty()) {
            date = newDate;
        }

        System.out.print("Current Time: " + time + "\nEnter new Time (or press Enter to keep): ");
        String newTime = pem.nextLine();
        if (!newTime.isEmpty()) {
            time = newTime;
        }

        System.out.println("Schedule updated successfully!");
    }

    void viewSchedule(){
        System.out.print("---- View Schedule ----");
        System.out.println("Title: "+ eventType);
        System.out.println("Date: "+ date);
        System.out.println("Time: "+ time);
    }

    void createEvent(List<Scheduling> allSchedules){
        Scanner pem = new Scanner(System.in);

        System.out.println("---- Create Event ----");

        System.out.print("Enter Event Type: ");
        this.eventType = pem.nextLine();

        System.out.print("Enter Date: ");
        this.date = pem.nextLine();

        System.out.print("Enter Time: ");
        this.time = pem.nextLine();

        System.out.print("Enter Venue: ");
        this.venue = pem.nextLine();

        allSchedules.add(this);

        System.out.println("Event created successfully!");
    }

    void editEvent(){
        Scanner pem = new Scanner(System.in);

        System.out.println("---- Edit Event ----");
        
        System.out.print("Current Event Type: " + eventType + "\nEnter new Event Type (or press Enter to keep): ");
        String newEventType = pem.nextLine();
        if (!newEventType.isEmpty()) {
            eventType = newEventType;
        }

        System.out.print("Current Date: " + date + "\nEnter new Date (or press Enter to keep): ");
        String newDate = pem.nextLine();
        if (!newDate.isEmpty()) {
            date = newDate;
        }

        System.out.print("Current Time: " + time + "\nEnter new Time (or press Enter to keep): ");
        String newTime = pem.nextLine();
        if (!newTime.isEmpty()) {
            time = newTime;
        }

        System.out.print("Current Venue: " + venue + "\nEnter new Venue (or press Enter to keep): ");
        String newVenue = pem.nextLine();
        if (!newVenue.isEmpty()) {
            venue = newVenue;
        }

        System.out.println("Event edited successfully!");
    }

    void displayAllSchedules(List<Scheduling> allSchedules){
        System.out.println("---- All Schedules ----");

        for (Scheduling s : allSchedules) {
        System.out.println("Title: "+ s.eventType);
        System.out.println("Date: "+ s.date);
        System.out.println("Time: "+ s.time);
        System.out.println("Venue: "+ s.venue);
        System.out.println("------------------------");
        }
    }
}
