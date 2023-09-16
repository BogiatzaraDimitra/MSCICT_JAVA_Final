package data;

/**
 *The class ProfessorCourse represents the database table that connects a professor's code with the course codes they teach.
 */

public class ProfessorCourse {
    private String PID;
    private String ID;

    public ProfessorCourse(String PID, String ID) {
        this.PID = PID;
        this.ID = ID;
    }

    public ProfessorCourse() {
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
