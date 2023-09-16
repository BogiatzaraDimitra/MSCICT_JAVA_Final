package data;
import java.sql.*;
import java.util.*;

/**
 * The Professor class represents a faculty member in the university database.
 * It extends the Person class, inheriting basic personal information, and adds
 * specific attributes related to professors such as a unique PID (Professor ID) and the course they teach.
 */
public class Professor extends Person
{
    /**
     *
     * @param PID
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param teaches
     */
    public Professor(String PID, String firstName, String lastName, String email, String phone, Course teaches) {
        super(firstName, lastName, email, phone);
        this.PID = PID;
        this.Teaches = teaches;
    }

    /**
     * Default Constructor of a Professor Object
     */
    public Professor(){}

    /**
     * Getter for PID
     * @return
     */
    public String getPID() {
        return PID;
    }

    /**
     * Setter for PID
     * @param PID
     */
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
        System.out.println("ID: ");
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

    public static void EditProfessor () throws SQLException {
        Scanner Keyb= new Scanner(System.in);
        String setColumn="";
        String updateField="";
        int sch;
        System.out.println("*** ΕΠΕΞΕΡΓΑΣΙΑ ΚΑΘΗΓΗΤΗ ***");
        System.out.println("ID: ");
        String pid= Keyb.nextLine();
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Professor WHERE PID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, pid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println(" ID: " +  resultSet.getString("PID")  + ", FirstName: " +  resultSet.getString("FirstName") + ", LastName: " +  resultSet.getString("LastName") + ", Phone: " +  resultSet.getString("Phone") + ", Email: " +  resultSet.getString("Email"));
                connection.close();
                do
                {
                    System.out.println("Επιλέξτε πεδίο προς επεξεργασία");
                    System.out.println("==========\n");
                    System.out.println("[1]...Όνομα");
                    System.out.println("[2]...Επώνυμο");
                    System.out.println("[3]...Email");
                    System.out.println("[4]...Τηλέφωνο");
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
                        case 9 -> {
                            return;
                        }
                    }
                    updateProfessor(setColumn,updateField,pid);
                }
                while (sch != 9);
            }else{
                System.out.println("Δεν βρέθηκε καθηγητής με αυτό το ID");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProfessor(String setColumn, String updateField, String id) throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
        String updateQuery = "UPDATE Professor SET " + setColumn + " = ? WHERE PID = ?";
        // Create a PreparedStatement
        PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery);
        // Set the new value for the column and the condition for the update
        updatePreparedStatement.setString(1, updateField); // Replace with the new value
        updatePreparedStatement.setString(2, id); // Replace with the condition value
        // Execute the update query
        int rowsAffected = updatePreparedStatement.executeUpdate();
        //Check if the update was successful and print the respective message
        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " εγγραφή ενημερώθηκε επιτυχώς.");
        } else {
            System.out.println("Ανεπιτυχής διόρθωση");
        }
    }

    public static void Teaches() throws SQLException {
        Scanner Keyb = new Scanner(System.in);
        System.out.println("*** ΑΝΑΘΕΣΗ ΜΑΘΗΜΑΤΟΣ ΚΑΘΗΓΗΤΗ ***");

        Connection connection;
        ResultSet resultSet;

        ProfessorCourse ProfessorToCourse = new ProfessorCourse();

        System.out.println("ID Καθηγητή:");
        ProfessorToCourse.setPID(Keyb.nextLine());
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Professor WHERE PID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, ProfessorToCourse.getPID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" ID: " + resultSet.getString("PID") + ", FirstName: " + resultSet.getString("FirstName") + ", LastName: " + resultSet.getString("LastName"));
                connection.close();
            }
            else {System.out.println("Δεν βρέθηκε καθηγητής με αυτό το ID");
            System.exit(0);}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ID μαθήματος το οποίο διδάκσει:");
        ProfessorToCourse.setID(Keyb.nextLine());
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT * FROM Course WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, ProfessorToCourse.getID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(" ID: " + resultSet.getString("ID") + ", Title: " + resultSet.getString("Name"));
                connection.close();
            }
            else{ System.out.println("Δεν βρέθηκε μάθημα με αυτό το ID");
            System.exit(0);}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        InsertTeaches(ProfessorToCourse.getPID(), ProfessorToCourse.getID());
    }

    public static void InsertTeaches(String pid, String id) throws SQLException {
        Connection connection;
        ResultSet resultSet;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String insertQuery = "INSERT INTO ProfessorCourse (PID, ID) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, pid);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();
            System.out.println("Η εγγραφή έγινε επιτυχώς.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void ShowProfCourse() throws SQLException {
        Scanner Keyb = new Scanner(System.in);

        System.out.println("*** ΛΙΣΤΑ ΜΑΘΗΜΑΤΩΝ ΚΑΘΗΓΗΤΗ ***");
        Connection connection;
        ResultSet resultSet, resultSet1;

        System.out.println("ID καθηγητή:");
        String pid, id;
        pid = Keyb.nextLine();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foititologio.db");
            String retrieveQuery = "SELECT ID FROM ProfessorCourse WHERE PID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
            preparedStatement.setString(1, pid);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String retrieveQuery2 = "SELECT Name FROM Course WHERE ID = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(retrieveQuery2);
                preparedStatement1.setString(1, resultSet.getString("ID"));
                resultSet1 = preparedStatement1.executeQuery();
                id = resultSet1.getString("Name");
                System.out.println("Course: " + id);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
