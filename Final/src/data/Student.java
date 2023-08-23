package data;

public class Student extends Person
{
    private String AM;
    private int Semester;
    private Course[] Studies;


    public Student (String AM, String FullName, String Email, String Phone, int Semester)
    {
        super (FullName, Email, Phone);
        this.AM = AM;
        this.Semester = Semester;
    }

}


