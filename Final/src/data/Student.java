package data;

import java.util.ArrayList;

public class Student extends Person
{
    private String AM;
    private int Semester;
    private ArrayList<Course> Studies = new ArrayList<Course>();
    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public ArrayList<Course> getStudies() {
        return Studies;
    }

    public void setStudies(ArrayList<Course> studies) {
        Studies = studies;
    }

    public Student(String firstName, String lastName, String email, String phone, String AM, int semester, ArrayList<Course> studies) {
        super(firstName, lastName, email, phone);
        this.AM = AM;
        Semester = semester;
        Studies = studies;
    }

    public Student(String firstName, String lastName, String email, String phone, String AM, int semester) {
        super(firstName, lastName, email, phone);
        this.AM = AM;
        Semester = semester;
    }
}
