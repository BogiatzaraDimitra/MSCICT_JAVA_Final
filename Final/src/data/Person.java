package data;

/**
 * Class that is used as a reference for every living entity of the university's database
 */
public class Person {
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    protected String FirstName;
    protected String LastName;
    protected String Email;
    protected String Phone;

    public Person(String firstName, String lastName, String email, String phone) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
    }
    public Person(){

    }

}


