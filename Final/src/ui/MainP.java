package ui;
import data.*;
import java.util.Scanner;
import java.sql.SQLException;

/**
 * Main Class That starts the program
 */
public class MainP {
    Scanner Keyb; //Scanner Class for User Input

    public MainP() {
        Keyb = new Scanner(System.in); //New Scanner Object
    }

    /**
     * Main method that starts the program
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        MainP Pr = new MainP();
        Pr.Menu1();
    }

    /**
     * Displays Main Menu
     * @throws SQLException
     */
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

    /**
     * Displays Student Menu for Professors
     * @throws SQLException
     */
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
            System.out.println("[5]...Ανάθεση Μαθήματος σε Καθηγητή");
            System.out.println("[6]...Προβολή Μαθημάτων Καθηγητή");
            System.out.println("[9]...Έ ξ ο δ ο ς");
            System.out.println("\nΕπιλογή : ");
            pch = Keyb.nextInt();
            switch (pch) {
                case 1 -> {Professor.ShowProf();
                            Pause();}
                case 2 -> {Professor.InsertProf ();
                            Pause();}
                case 3 -> {Professor.EditProfessor();
                            Pause();}
                case 4 -> {Professor.DeleteProfessor();
                            Pause();}
                case 5 -> {Professor.Teaches();
                    Pause();}
                case 6 -> {Professor.ShowProfCourse();
                Pause();}
            }
            }
            while (pch != 9);
        }

    /**
     * Displays User Menu for Students
     * @throws SQLException
     */
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
            System.out.println("[5]...Εγγραφή Φοιτητή σε μάθημα");
            System.out.println("[6]...Προβολή μαθημάτων φοιτητή");
            System.out.println("[7]...Εισαγωγή Βαθμολογίας σε φοιτητή");
            System.out.println("[8]...Εμφάνιση Μέσου όρου φοιτητή");
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
                case 5 -> {Student.Studies();
                            Pause();}
                case 6 -> {Student.ShowStudentCourse();
                            Pause();}
                case 7 -> {Student.GradeToStudent();
                            Pause();}
                case 8 -> {Student.CalcAvg ();
                            Pause();}
            }
        }
        while (sch != 9);
    }

    /**
     * Displays User Menu for Courses
     * @throws SQLException
     */
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
            System.out.println("[5]...Μέσος Όρος Βαθμολογίας");
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
                case 5 -> {Course.CalcAvg ();
                            Pause();}
            }
        }
        while (cch != 9);
    }

    /**
     * Method that pauses the program and gives time to the user to read the results
     */
    public void Pause ()
    {
        Scanner K = new Scanner (System.in);
        System.out.print ("Πιέστε <Enter> για συνέχεια...");
        K.nextLine ();
    }
}
