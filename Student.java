import java.util.List;

public class Student extends User{

    public Student(int role, String name, String password) {
        super(role, name, password);
    }

    Scheduling sched = new Scheduling();

    public void setSchedule(List<Scheduling> allSchedules) {
        sched.setSchedule(allSchedules);
    }

    public void updateSchedule(){
        sched.updateSchedule();
    }

    public void viewSchedule(){
        sched.viewSchedule();
    }
}
