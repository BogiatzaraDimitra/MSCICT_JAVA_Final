//Imports the local packages
package ui;
import data.*;

//Imports Utilities
import java.util.Scanner;

//Imports Libraries for the Database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainP {
    Scanner Keyb;

    public MainP() {
        Keyb = new Scanner(System.in);
    }

    //Main Method used in program
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
                case 4 -> Menu4();
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
                case 2 -> InsertProf ();
                case 4 -> DeleteProfessor ();
            }
            }
            while (pch != 9);
        }

    public void Menu3 () throws SQLException {

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
                case 1 ->  Student.ShowStudents();
                case 2 -> Student.InsertStudent();
                case 3 -> Student.EditStudents();
                case 4 -> Student.DeleteStudent();
            }
        }
        while (sch != 9);
    }

    public void Menu4 () throws SQLException {

        int cch=0;
        do
        {
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
                case 2 -> InsertCourse ();
                case 3 -> DeleteCourse ();
            }
        }
        while (cch != 9);
    }

    public void ShowProf () throws SQLException {
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
        Pause();
    }

        public void ShowCourse () throws SQLException {
            System.out.println("*** ΛΙΣΤΑ ΜΑΘΗΜΑΤΩΝ ***");
            Connection connection = null;
            ResultSet resultSet = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
                String retrieveQuery = "SELECT * FROM Course";
                PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String name= resultSet.getString("Name");
                    int sem = resultSet.getInt("Semester");
                    System.out.println(" ID: " + id + ", Name: " + name + ", Semester: " + sem);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        Pause();
    }



    public void InsertProf () throws SQLException {
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΚΑΘΗΓΗΤΗ ***");
        Connection connection = null;
        ResultSet resultSet = null;
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
            //String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            //resultSet = preparedStatement.executeQuery();

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
        Pause();
    }



    public void InsertCourse () throws SQLException {
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΜΑΘΗΜΑΤΟΣ ***");
        Connection connection = null;
        ResultSet resultSet = null;
        String id, Name;
        int sem;
        Keyb.nextLine();
        System.out.println("ID: ");
        id = Keyb.nextLine();
        System.out.println("Name: ");
        Name = Keyb.nextLine();
        System.out.println("Semester: ");
        sem = Keyb.nextInt();

        Course NewCourse = new Course(id, Name, sem);

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "INSERT INTO Course (ID,Name,Semester) VALUES (?,?,?)";
            //String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            //resultSet = preparedStatement.executeQuery();

            preparedStatement.setString(1, NewCourse.getID());
            preparedStatement.setString(2, NewCourse.getName());
            preparedStatement.setInt(3, NewCourse.getSemester());

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pause();
    }

    public void DeleteCourse () throws SQLException {
        System.out.println("*** ΔΙΑΓΡΑΦΗ ΜΑΘΗΜΑΤΟΣ ***");
        Connection connection = null;
        ResultSet resultSet = null;
        String id;
        Keyb.nextLine();
        System.out.println("ID: ");
        id = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "DELETE FROM Course WHERE id = ?";
            //String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            //resultSet = preparedStatement.executeQuery();

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pause();
    }

    public void DeleteProfessor () throws SQLException {
        System.out.println("*** ΔΙΑΓΡΑΦΗ ΚΑΘΗΓΗΤΗ ***");
        Connection connection = null;
        ResultSet resultSet = null;
        String id;
        Keyb.nextLine();
        System.out.println("ID: ");
        id = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "DELETE FROM Professor WHERE PID = ?";
            //String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            //resultSet = preparedStatement.executeQuery();

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pause();
    }


    public void Pause ()
    {
        Scanner K = new Scanner (System.in);
        System.out.print ("Πιέστε <Enter> για συνέχεια...");
        K.nextLine ();
    }
}
