import java.util.List;

public class Admin extends User{
    public Admin(int role, String name, String password) {
        super(role, name, password);
    }

    Scheduling sched = new Scheduling();

    public void createEvent(List<Scheduling> allSchedules){
        sched.createEvent(allSchedules);
    }

    public void editEvent(){
        sched.editEvent();
    }
}
