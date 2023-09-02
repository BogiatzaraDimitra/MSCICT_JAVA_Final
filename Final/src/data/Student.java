package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    public Student(){}
    public static void InsertStudent () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΦΟΙΤΗΤΗ ***");
        Connection connection;
        //ResultSet resultSet = null;
        Student newStudent= new Student();
        System.out.println("AM: ");
        newStudent.setAM(Keyb.nextLine());
        System.out.println("First Name: ");
        newStudent.setFirstName(Keyb.nextLine());
        System.out.println("Last Name: ");
        newStudent.setLastName(Keyb.nextLine());
        System.out.println("Phone: ");
        newStudent.setPhone(Keyb.nextLine());
        System.out.println("Email: ");
        newStudent.setEmail(Keyb.nextLine());
        System.out.println("Semester: ");
        newStudent.setSemester(Keyb.nextInt());

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "INSERT INTO Student (AM,FirstName,LastName,Phone,Email,Semester) VALUES (?,?,?,?,?,?)";
            //String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            //resultSet = preparedStatement.executeQuery();

            preparedStatement.setString(1, newStudent.getAM());
            preparedStatement.setString(2, newStudent.getFirstName());
            preparedStatement.setString(3, newStudent.getLastName());
            preparedStatement.setString(4, newStudent.getPhone());
            preparedStatement.setString(5, newStudent.getEmail());
            preparedStatement.setInt(6, newStudent.getSemester());

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void DeleteStudent () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΔΙΑΓΡΑΦΗ ΦΟΙΤΗΤΗ ***");
        Connection connection;
        String am;
        Keyb.nextLine();
        System.out.println("AM: ");
        am = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "DELETE FROM Student WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, am);

            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void ShowStudents () throws SQLException {
        System.out.println("*** ΛΙΣΤΑ ΦΟΙΤΗΤΩΝ ***");
        Connection connection;
        ResultSet resultSet ;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Student";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String am = resultSet.getString("AM");
                String firstname = resultSet.getString("FirstName");
                String lastname = resultSet.getString("LastName");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                int sem = resultSet.getInt("Semester");
                System.out.println(" AM: " + am + ", FirstName: " + firstname + ", LastName: " + lastname + ", Phone: " + phone + ", Email: " + email + " Semester: " + sem);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void EditStudents () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        String setColumn="";
        String updateField="";
        int sch;
        System.out.println("*** ΕΠΕΞΕΡΓΑΣΙΑ ΦΟΙΤΗΤΗ ***");
        System.out.println("AM: ");
        String am= Keyb.nextLine();
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Student WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println(" AM: " +  resultSet.getString("AM")  + ", FirstName: " +  resultSet.getString("FirstName") + ", LastName: " +  resultSet.getString("LastName") + ", Phone: " +  resultSet.getString("Phone") + ", Email: " +  resultSet.getString("Email") + " Semester: " +  resultSet.getString("Semester"));
                connection.close();
                do
                {
                    System.out.println("Επιλέξτε πεδίο προς επεξεργασία");
                    System.out.println("==========\n");
                    System.out.println("[1]...Όνομα");
                    System.out.println("[2]...Επώνυμο");
                    System.out.println("[3]...Email");
                    System.out.println("[4]...Τηλέφωνο");
                    System.out.println("[5]...Εξάμηνο");
                    System.out.println("[9]...Έ ξ ο δ ο ς");
                    System.out.println("\nΕπιλογή : ");
                    sch= Keyb.nextInt();
                    Keyb.nextLine();
                    switch (sch) {
                        case 1 -> {
                            setColumn = "FirstName";
                            System.out.println("Πληκτρολογήστε νέο όνομα: ");
                            updateField = Keyb.nextLine();
                        }
                        case 2 -> {
                            setColumn = "LastName";
                            System.out.println("Πληκτρολογήστε νέο επίθετο: ");
                            updateField = Keyb.nextLine();

                        }
                        case 3 -> {
                            setColumn = "Email";
                            System.out.println("Πληκτρολογήστε νέο email: ");
                            updateField = Keyb.nextLine();

                        }
                        case 4 -> {
                            setColumn = "Phone";
                            System.out.println("Πληκτρολογήστε νέο τηλέφωνο: ");
                            updateField = Keyb.nextLine();

                        }
                        case 5 -> {
                            setColumn = "Semester";
                            System.out.println("Πληκτρολογήστε νέο εξάμηνο: ");
                            updateField = Keyb.nextLine();

                        }
                    }
                    updateStudent(setColumn,updateField,am);
                }
                while (sch != 9);
            }else{
                System.out.println("Δεν βρέθηκε φοιτητής με αυτό το ΑΜ");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateStudent(String setColumn, String updateField, String am) throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
        String updateQuery = "UPDATE Student SET " + setColumn + " = ? WHERE AM = ?";
        // Create a PreparedStatement
        PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery);
        // Set the new value for the column and the condition for the update
        updatePreparedStatement.setString(1, updateField); // Replace with the new value
        updatePreparedStatement.setString(2, am); // Replace with the condition value
        // Execute the update query
        int rowsAffected = updatePreparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " εγγραφή ενημερώθηκε επιτυχώς.");
        } else {
            System.out.println("Ανεπιτυχής διόρθωση");
        }
    }

}
