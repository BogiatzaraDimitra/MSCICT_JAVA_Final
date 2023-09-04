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
