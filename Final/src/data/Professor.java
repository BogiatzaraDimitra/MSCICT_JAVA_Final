package data;

import java.sql.*;
import java.util.Scanner;

public class Professor extends Person
{

    Scanner Keybo = new Scanner(System.in);

    public Professor(String PID, String firstName, String lastName, String email, String phone, Course teaches) {
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

    public static void ShowProf() throws SQLException {
        System.out.println("*** ΛΙΣΤΑ ΚΑΘΗΓΗΤΩΝ ***");
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Professor";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("PID");
                String name = resultSet.getString("FirstName");
                String lastname= resultSet.getString("LastName");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                System.out.println("ID: " + id + ", Firstname: " + name + ", Lastname: " + lastname + ", Phone: " + phone + ", Email: " + email);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void InsertProf() throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΚΑΘΗΓΗΤΗ ***");
        Connection connection;
        String PID, FName,LName,phone,email;
        Course teaches = null;

        Keyb.nextLine();
        System.out.println("AM: ");
        PID = Keyb.nextLine();
        System.out.println("First Name: ");
        FName = Keyb.nextLine();
        System.out.println("Last Name: ");
        LName = Keyb.nextLine();
        System.out.println("Phone: ");
        phone = Keyb.nextLine();
        System.out.println("Email: ");
        email = Keyb.nextLine();


        Professor NewProfessor = new Professor (PID, FName, LName, email, phone, teaches = null);

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "INSERT INTO Professor (PID,FirstName,LastName,Phone,Email) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, NewProfessor.getPID());
            preparedStatement.setString(2, NewProfessor.getFirstName());
            preparedStatement.setString(3, NewProfessor.getLastName());
            preparedStatement.setString(4, NewProfessor.getPhone());
            preparedStatement.setString(5, NewProfessor.getEmail());

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void DeleteProfessor () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΔΙΑΓΡΑΦΗ ΚΑΘΗΓΗΤΗ ***");
        Connection connection;
        String id;
        Keyb.nextLine();
        System.out.println("ID: ");
        id = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "DELETE FROM Professor WHERE PID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);


            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
