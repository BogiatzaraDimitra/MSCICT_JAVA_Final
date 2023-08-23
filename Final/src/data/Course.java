package data;

/**
 * Η κλάση Course παριστά ένα μάθημα του προγράμματος σπουδών.
 * Υπάρχουν και οι κατάλληλοι getters που παρέχουν πρόσβαση σε όλες τις κλάσεις.
 *
 */

public class Course
{
    String ID;
    String Name;
    int Semester;

    public Course (String I, String N, int Sem)
    {
        this.ID = I;
        this.Name = N;
        Semester = Sem;
    }

    public String getID () {return ID;}
    public void setID (String ID) {this.ID = ID;}
    public String getName () {return Name;}
    public void setName (String Name) {this.Name = Name;}
    public int getSemester () {return Semester;}
    public void setSemester (int Semester) {Semester = Semester;}
}
