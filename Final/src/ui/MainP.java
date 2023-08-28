//Imports the local packages
package ui;
import data.Course;
import data.Professor;
import data.Student;

//Imports Utilities
import java.util.Scanner;

//Imports Libraries for the Database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainP {
    Professor[] Profs;
    Student[] Studs;
    Course[] AllCourses;
    Scanner Keyb;

    public MainP() {
        Profs = new Professor[20];
        //SomeProfs ();
        Studs = new Student[20];
        //SomeStuds ();
        AllCourses = new Course[20];
        //SomeCourse ();
        Keyb = new Scanner(System.in);
    }

    public static void main(String[] args) throws SQLException {
        //Settings for connection to the Database
       Connection connection = null;
       ResultSet resultSet = null;
        //TestCode
        /*try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            PreparedStatement preparedStatement = null;
            // Insert data
            String insertQuery = "INSERT INTO Course (ID,Name) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "2");
            preparedStatement.setString(2, "Programming");

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");

            // Retrieve data
            String retrieveQuery = "SELECT ID, Name FROM Course";
            preparedStatement = connection.prepareStatement(retrieveQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
        MainP Pr = new MainP();
        Pr.Menu1();
    }

    public void Menu1 () throws SQLException {
        int ch;
        do {
            System.out.println("Βασικό Μενού");
            System.out.println("===============\n");
            System.out.println("[1]...Πίνακας Καθηγητών");
            System.out.println("[2]...Πίνακας Φοιτητών");
            System.out.println("[3]...Πίνακας Μαθημάτων");
            System.out.println("[9]...Έ ξ ο δ ο ς");
            System.out.println("\nΕπιλογή : ");
            ch = this.Keyb.nextInt();
            this.Keyb.nextLine();
            switch (ch) {
                case 1 -> Menu2();
                case 2 -> Menu3();
                case 3 -> Menu4();
            }
        } while (ch != 9);
    }

    public void Menu2 () throws SQLException {
            int pch=0;
            do
            {
            System.out.println("Καθηγητές");
            System.out.println("==========\n");
            System.out.println("[1]...Προβολή Καθηγητών");
            System.out.println("[2]...Εισαγωγή Καθηγητή");
            System.out.println("[3]...Διόρθωση Καθηγητή");
            System.out.println("[4]...Διαγραφή Καθηγητή");
            System.out.println("[9]...Έ ξ ο δ ο ς");
            System.out.println("\nΕπιλογή : ");
            pch = Keyb.nextInt();
            switch (pch) {
                case 1 -> ShowProf();
            }
            }
            while (pch != 9);
        }

    public void Menu3 ()
    {

        int sch=0;
        do
        {
            System.out.println("Φοιτητές");
            System.out.println("==========\n");
            System.out.println("[1]...Προβολή Φοιτητών");
            System.out.println("[2]...Εισαγωγή Φοιτητή");
            System.out.println("[3]...Διόρθωση Φοιτητή");
            System.out.println("[4]...Διαγραφή Φοιτητή");
            System.out.println("[9]...Έ ξ ο δ ο ς");
            System.out.println("\nΕπιλογή : ");
            sch = Keyb.nextInt();
            switch (sch) {
                case 1 -> {
                    ;
                }
                case 2 -> {
                    ;
                }
                case 3 -> {
                    ;
                }
                case 4 -> {
                    ;
                }
            }
        }
        while (sch != 9);
    }

    public void Menu4 ()
    {

        int cch=0;
        do
        {
        if (cch!=9) {
            System.out.println("Μαθήματα");
            System.out.println("==========\n");
            System.out.println("[1]...Προβολή Μαθημάτων");
            System.out.println("[2]...Εισαγωγή Μαθήματος");
            System.out.println("[3]...Διόρθωση Μαθήματος");
            System.out.println("[4]...Διαγραφή Μαθήματος");
            System.out.println("[9]...Έ ξ ο δ ο ς");
            System.out.println("\nΕπιλογή : ");
            cch = Keyb.nextInt();
            switch (cch) {
                case 1 -> ShowCourse();
            }
        }
        }
        while (cch != 9);
    }

    public void ShowProf () throws SQLException {
        System.out.println("*** ΛΙΣΤΑ ΚΑΘΗΓΗΤΩΝ ***");
        Connection connection = null;
        ResultSet resultSet = null;
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
        Pause();
    }

        public void ShowCourse ()
    {
        int i;
        System.out.println("*** ΛΙΣΤΑ ΜΑΘΗΜΑΤΩΝ ***");
        for (i = 0; i < 10; i++)
            if (AllCourses[i] != null)
                System.out.printf("%2d   %-20s  %-20s  %s\n", i + 1, AllCourses[i].getName(), AllCourses[i].getID(), AllCourses[i].getSemester());
        Pause();
    }

    public void Pause ()
    {
        Scanner K = new Scanner (System.in);
        System.out.print ("Πιέστε <Enter> για συνέχεια...");
        K.nextLine ();
    }

}
