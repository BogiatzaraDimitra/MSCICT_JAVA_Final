package data;

public class Professor extends Person
{
    private String PID; // kodikos ka8igiti
    private Course Teaches; //eidikotita - ma8hma

    public Professor (String PID, String FullName, String Email, String Phone, Course Teaches)
    {
        super (FullName, Email, Phone);
        this.PID = PID;
        this.Teaches = Teaches;
    }

    public String getPID () {return PID;}
    public void setPID (String PID) {this.PID = PID;}


    public Course getTeaches() {return Teaches;}

    public void setTeaches (Course Teaches) { this.Teaches = Teaches; }
}
