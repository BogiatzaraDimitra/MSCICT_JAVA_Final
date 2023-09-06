package data;

/**
 * Η κλάση ProfessorCourse παριστά τον πίνακα της βάσης δεδομένων που συνδέει έναν κψδικό καθηγητή με τους κωδικούε μαθήμάτων που διδάσκει.
 * Υπάρχουν και οι κατάλληλοι getters που παρέχουν πρόσβαση σε όλες τις κλάσεις.
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
