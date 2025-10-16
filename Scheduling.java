public class Scheduling {
    String eventType;
    String date;
    String time;
    String venue;

    java.util.Scanner sc = new java.util.Scanner(System.in);

    void createEvent() {
        System.out.print("Enter event type: ");
        eventType = sc.nextLine();
        System.out.print("Enter date: ");
        date = sc.nextLine();
        System.out.print("Enter time: ");
        time = sc.nextLine();
        System.out.print("Enter venue: ");
        venue = sc.nextLine();

        System.out.println("Event created successfully!");

        // 🧩 Save to CSV here
        PEMSYS_Main app = new PEMSYS_Main();
        app.saveToCSV("events.csv", eventType + "," + date + "," + time + "," + venue);
    }

    void setSchedule() {
        System.out.print("Enter event type: ");
        eventType = sc.nextLine();
        System.out.print("Enter date: ");
        date = sc.nextLine();
        System.out.print("Enter time: ");
        time = sc.nextLine();

        System.out.println("Schedule set successfully!");

        PEMSYS_Main app = new PEMSYS_Main();
        app.saveToCSV("schedules.csv", eventType + "," + date + "," + time);
    }

    void viewSchedule() {
        System.out.println("Viewing all schedules (dummy view)...");
    }
}
