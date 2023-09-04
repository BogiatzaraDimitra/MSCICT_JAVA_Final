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
                case 3 -> Menu4();
            }
        } while (ch != 9);
    }

    public void Menu2 () throws SQLException {
            int pch;

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
                case 1 -> {Professor.ShowProf();
                            Pause();}
                case 2 -> {Professor.InsertProf ();
                            Pause();}
                case 4 -> {Professor.DeleteProfessor();
                            Pause();}
            }
            }
            while (pch != 9);
        }

    public void Menu3 () throws SQLException {

        int sch;
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
                case 1 -> {Student.ShowStudents();
                            Pause();}
                case 2 -> {Student.InsertStudent();
                            Pause();}
                case 3 -> {Student.EditStudents();
                            Pause();}
                case 4 -> {Student.DeleteStudent();
                            Pause();}
            }
        }
        while (sch != 9);
    }

    public void Menu4 () throws SQLException {

        int cch;
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
                case 1 -> {Course.ShowCourse();
                            Pause();}
                case 2 -> {Course.InsertCourse ();
                            Pause();}
                case 3 -> {Course.EditCourse ();
                            Pause();}
                case 4 -> {Course.DeleteCourse ();
                            Pause();}
            }
        }
        while (cch != 9);
    }




    public void Pause ()
    {
        Scanner K = new Scanner (System.in);
        System.out.print ("Πιέστε <Enter> για συνέχεια...");
        K.nextLine ();
    }
}
