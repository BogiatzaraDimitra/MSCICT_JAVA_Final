package data;

public class Professor extends Person
{
    public Professor(String firstName, String lastName, String email, String phone, String PID, Course teaches) {
        super(firstName, lastName, email, phone);
        this.PID = PID;
        this.Teaches = teaches;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

   public Course getTeaches() {
        return Teaches;
    }

    public void setTeaches(Course teaches) {
        Teaches = teaches;
    }

    private String PID; // Κωδικός Καθηγητή
    private Course Teaches; //Μάθημα
}
