package data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Student class represents a student in the university database.
 * It extends the Person class, inheriting basic personal information, and
 * includes additional attributes specific to students, such as their unique
 * AM (Admission Number), current semester, and the list of courses they are
 * currently studying.
 */
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

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param AM
     * @param semester
     * @param studies
     */
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

    /**
     * Method that displays every Student in the database
     * @throws SQLException
     */
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

    /**
     * Method that allows the user to add a new Student to the databade
     * @throws SQLException
     */
    public static void InsertStudent () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        System.out.println("*** ΕΙΣΑΓΩΓΗ ΦΟΙΤΗΤΗ ***");
        Connection connection;
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
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

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

    /**
     * Method that allows the user to edit any attribute of an existing student in the database
     * @throws SQLException
     */
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
                        case 9 -> {
                            return;
                        }
                    }
                    updateStudent(setColumn,updateField,am);
                }
                while (sch != 9);
            }else{
                System.out.println("Δεν βρέθηκε φοιτητής με αυτό το ΑΜ");
                System.exit(0);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Used by methos EditStudent() and writes any changes into the database
     * @param setColumn
     * @param updateField
     * @param am
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * Method that lets the user delete a Student by their AM
     * @throws SQLException
     */
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

    /**
     * Method that lets the user enroll a student into a course
     * by adding the AM of the Student and the ID od the Course
     * @throws SQLException
     */
    public static void Studies() throws SQLException {
        Scanner Keyb = new Scanner(System.in);
        System.out.println("*** ΑΝΑΘΕΣΗ ΜΑΘΗΜΑΤΟΣ ΣΕ ΦΟΙΤΗΤΗ ***");

        Connection connection;
        ResultSet resultSet;
        String am, id;

        System.out.println("ΑΜ Φοιτητή:");
        am = Keyb.nextLine();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Student WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" AM: " + resultSet.getString("AM") + ", FirstName: " + resultSet.getString("FirstName") + ", LastName: " + resultSet.getString("LastName"));
                connection.close();
            }
            else System.out.println("Δεν βρέθηκε φοιτητής με αυτό το ΑΜ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ID μαθήματος:");
        id = Keyb.nextLine();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Course WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" ID: " + resultSet.getString("ID") + ", Title: " + resultSet.getString("Name"));
                connection.close();
            }
            else System.out.println("Δεν βρέθηκε μάθημα με αυτό το ID");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "INSERT INTO StudentCourse (AM, ID, Grade) VALUES (?,?,-1)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, am);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();
            System.out.println("Η εγγραφή έγινε επιτυχώς.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection.close();

    }

    /**
     * Method that Shows the courses a student is enrolled at
     * @throws SQLException
     */
    public static void ShowStudentCourse() throws SQLException {
        Scanner Keyb = new Scanner(System.in);

        System.out.println("*** ΛΙΣΤΑ ΜΑΘΗΜΑΤΩΝ ΦΟΙΤΗΤΗ ***");
        Connection connection;
        ResultSet resultSet, resultSet1;

        System.out.println("ID Φοιτητη:");
        String am, id;
        int grad;
        am = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM StudentCourse WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String retrieveQuery2 = "SELECT Name FROM Course WHERE ID = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(retrieveQuery2);
                preparedStatement1.setString(1, resultSet.getString("ID"));
                resultSet1 = preparedStatement1.executeQuery();
                id = resultSet1.getString("Name");
                grad = resultSet.getInt("Grade");
                System.out.printf("Course: " + id + "   ");
                if(grad != -1){
                System.out.printf("Grade: " + grad);}
                System.out.println("");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that alllows the user to assing a grade to a student
     * @throws SQLException
     */
    public static void GradeToStudent() throws SQLException {
        Scanner Keyb = new Scanner(System.in);

        Connection connection;
        ResultSet resultSet;

        String am, id;
        int grad;

        System.out.println("*** ΒΑΘΜΟΛΟΓΙΑ ΦΟΙΤΗΤΗ ***");
        System.out.println("ID Φοιτητη:");
        am = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Student WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" AM: " + resultSet.getString("AM") + ", FirstName: " + resultSet.getString("FirstName") + ", LastName: " + resultSet.getString("LastName"));
                connection.close();
            }
            else {System.out.println("Δεν βρέθηκε φοιτητής με αυτό το ΑΜ");
            System.exit(0);}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ID Μαθήματος:");
        id = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Course WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" ID: " + resultSet.getString("ID") + ", Title: " + resultSet.getString("Name"));
                connection.close();
            }
            else {System.out.println("Δεν βρέθηκε μάθημα με αυτό το ID");
            System.exit(0);}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM StudentCourse WHERE AM = ? AND ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                connection.close();
            }
            else {System.out.println("Ο φοιτητής δεν είναι εγγεγραμμένος στο μάθημα");
                System.exit(0);}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Βαθμός Φοιτητή για το μάθημα:");
        grad = Keyb.nextInt();
        try {
            checkgrade(grad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String updateQuery = "UPDATE StudentCourse SET Grade = ? WHERE AM = ? AND ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setInt(1, grad);
            preparedStatement.setString(2, am);
            preparedStatement.setString(3, id);

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");

            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
}

    /**
     * Method that reads the grades assigned to a student and returns the average
     * @throws SQLException
     */
    public static void CalcAvg () throws SQLException {
        Scanner Keyb = new Scanner(System.in);
        System.out.println("*** ΜΕΣΟΣ ΟΡΟΣ ΦΟΙΤΗΤΗ ***");
        Connection connection;
        ResultSet resultSet ;

        float avg = 0;
        float grad, i = 0;
        System.out.println("Ειράγετε ΑΜ του φοιτητή για εξαγωγή μέσου όρου");
        String am = Keyb.nextLine();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT Grade FROM StudentCourse WHERE AM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, am);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //if (resultSet != null){
                grad = resultSet.getInt("Grade");
                if (grad >= 5 ){
                avg = avg + grad;
                i = i+1;}
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Ο μέσος όρος του φοιτητή με AM " + am + " είναι " + avg / i);
    }

    /**
     * Checks if a numeric value is within range 0 - 10
     * @param gr
     * @throws Exception
     */
    public static void checkgrade(int gr) throws Exception{
        if (gr < 0 || gr > 10) {
            throw new Exception("Number is out of the range [0, 10]: " + gr);
        }
    }
}
