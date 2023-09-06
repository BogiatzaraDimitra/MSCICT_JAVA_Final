package data;

import java.sql.*;
import java.util.Scanner;

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


    public static void ShowCourse () throws SQLException {
        System.out.println("*** ΛΙΣΤΑ ΜΑΘΗΜΑΤΩΝ ***");
        Connection connection;
        ResultSet resultSet;
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
    }

    public static void EditCourse () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        String setColumn="";
        String updateField="";
        int sch;
        System.out.println("*** ΕΠΕΞΕΡΓΑΣΙΑ ΜΑΘΗΜΑΤΟΣ ***");
        System.out.println("ID: ");
        String ID= Keyb.nextLine();
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Course WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, ID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println(" ID: " +  resultSet.getString("ID")  + ", Name: " +  resultSet.getString("Name") + ", Semester: " +  resultSet.getInt("Semester") );
                connection.close();
                do
                {
                    System.out.println("Επιλέξτε πεδίο προς επεξεργασία");
                    System.out.println("==========\n");
                    System.out.println("[1]...Όνομα");
                    System.out.println("[2]...Εξάμηνο");
                    System.out.println("[9]...Έ ξ ο δ ο ς");
                    System.out.println("\nΕπιλογή : ");
                    sch= Keyb.nextInt();
                    Keyb.nextLine();
                    switch (sch) {
                        case 1 -> {
                            setColumn = "Name";
                            System.out.println("Πληκτρολογήστε νέο τίτλο: ");
                            updateField = Keyb.nextLine();
                        }
                        case 2 -> {
                            setColumn = "Semester";
                            System.out.println("Πληκτρολογήστε νέο εξάμηνο: ");
                            updateField = Keyb.nextLine();

                        }
                        case 9 -> {
                            return;
                        }

                    }
                    updateCourse(setColumn,updateField,ID);
                }
                while (sch != 9);
            }else{
                System.out.println("Δεν βρέθηκε μάθημα με αυτό το ID");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCourse(String setColumn, String updateField, String ID) throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
        String updateQuery = "UPDATE Course SET " + setColumn + " = ? WHERE ID = ?";
        // Create a PreparedStatement
        PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery);
        // Set the new value for the column and the condition for the update
        updatePreparedStatement.setString(1, updateField); // Replace with the new value
        updatePreparedStatement.setString(2, ID); // Replace with the condition value
        // Execute the update query
        int rowsAffected = updatePreparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " εγγραφή ενημερώθηκε επιτυχώς.");
        } else {
            System.out.println("Ανεπιτυχής διόρθωση");
        }
    }

    public static void InsertCourse () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΜΑΘΗΜΑΤΟΣ ***");
        Connection connection;
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
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, NewCourse.getID());
            preparedStatement.setString(2, NewCourse.getName());
            preparedStatement.setInt(3, NewCourse.getSemester());

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DeleteCourse () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΔΙΑΓΡΑΦΗ ΜΑΘΗΜΑΤΟΣ ***");
        Connection connection;
        String id;
        Keyb.nextLine();
        System.out.println("ID: ");
        id = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "DELETE FROM Course WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CalcAvg () throws SQLException {
        Scanner Keyb = new Scanner(System.in);
        System.out.println("*** ΜΕΣΟΣ ΟΡΟΣ ΒΑΘΜΟΛΟΓΙΑΣ ΜΑΘΗΜΑΤΟΣ ***");
        Connection connection;
        ResultSet resultSet ;

        float avg = 0;
        int grad, i = 0;
        System.out.println("Ειράγετε κωδικό μαθήματος");
        String am = Keyb.nextLine();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT Grade FROM StudentCourse WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grad = resultSet.getInt("Grade");
                avg = avg + grad;
                i++;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Ο μέσος όρος βαθμολογίας για το μάθημα με κωδικό " + am + " is " + avg / i);
    }
}
