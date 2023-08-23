package data;

public class Person
{
    protected String AM;
    protected String FullName;
    protected String Email;
    protected String Phone;
    private static int Counter = 0;

    public Person(String FullName, String Email, String Phone)
    {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        Counter++;
    }
    public String getAM () {return AM;}
    public void setAM (String AM) {this.AM = AM;}
    public String getFullName () {return FullName;}
    public void setFullName (String FullName) {this.FullName = FullName;}
    public String getEmail () {return Email;}
    public void setEmail (String Email) {this.Email = Email;}
    public String getPhone() {return Phone;}
    public void setPhone (String Phone) {this.Phone = Phone;}

}
