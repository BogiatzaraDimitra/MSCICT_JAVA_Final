package ui;
import data.Course;
import data.Professor;
import data.Student;

import java.util.Scanner;

public class MainP
{
    Professor[] Profs;
    Student[] Studs;
    Course[] AllCourses;
    Scanner Keyb;

    public MainP ()
    {
        Profs = new Professor[20];
        //SomeProfs ();
        Studs = new Student[20];
        //SomeStuds ();
        AllCourses = new Course[20];
        //SomeCourse ();
        Keyb = new Scanner (System.in);
    }

    public static void main (String[] args)
    {
        MainP Pr = new MainP ();
        Pr.Menu1 ();
        Pr.Menu2 ();
        Pr.Menu3 ();
        Pr.Menu4 ();
    }
    public void Menu1 ()
    {
        int ch;
        do
        {
            System.out.println ("Βασικό Μενού");
            System.out.println ("===============\n");
            System.out.println ("[1]...Πίνακας Καθηγητών");
            System.out.println ("[2]...Πίνακας Φοιτητών");
            System.out.println ("[3]...Πίνακας Μαθημάτων");
            System.out.println ("[9]...Έ ξο δ ο ς");
            System.out.println ("\nΕπιλογή : ");
            ch = Keyb.nextInt ();
            switch (ch)
            {
                case 1: Menu2 (); break;
                case 2: Menu3 (); break;
                case 3: Menu4 (); break;
            }
        }
        while (ch != 9);
    }

    public void Menu2 ()
    {

            int pch;
            do
            {
                System.out.println ("Καθηγητές");
                System.out.println ("==========\n");
                System.out.println ("[1]...Προβολή Καθηγητών");
                System.out.println ("[2]...Εισαγωγή Καθηγητή");
                System.out.println ("[3]...Διόρθωση Καθηγητή");
                System.out.println ("[4]...Διαγραφή Καθηγητή");
                System.out.println ("[9]...Έ ξο δ ο ς");
                System.out.println ("\nΕπιλογή : ");
                pch = Keyb.nextInt ();
                switch (pch)
                {
                    case 1: ShowProf (); break;
                    case 2: ; break;
                    case 3: ; break;
                    case 4: ; break;
                }
            }
            while (pch != 9);
        }

    public void Menu3 ()
    {

        int sch;
        do
        {
            System.out.println ("Φοιτητές");
            System.out.println ("==========\n");
            System.out.println ("[1]...Προβολή Φοιτητών");
            System.out.println ("[2]...Εισαγωγή Φοιτητή");
            System.out.println ("[3]...Διόρθωση Φοιτητή");
            System.out.println ("[4]...Διαγραφή Φοιτητή");
            System.out.println ("[9]...Έ ξο δ ο ς");
            System.out.println ("\nΕπιλογή : ");
            sch = Keyb.nextInt ();
            switch (sch)
            {
                case 1: ; break;
                case 2: ; break;
                case 3: ; break;
                case 4: ; break;
            }
        }
        while (sch != 9);
    }

    public void Menu4 ()
    {

        int cch;
        do
        {
            System.out.println ("Μαθήματα");
            System.out.println ("==========\n");
            System.out.println ("[1]...Προβολή Μαθημάτων");
            System.out.println ("[2]...Εισαγωγή Μαθήματος");
            System.out.println ("[3]...Διόρθωση Μαθήματος");
            System.out.println ("[4]...Διαγραφή Μαθήματος");
            System.out.println ("[9]...Έ ξο δ ο ς");
            System.out.println ("\nΕπιλογή : ");
            cch = Keyb.nextInt ();
            switch (cch)
            {
                case 1: ShowCourse (); break;
                case 2: ; break;
                case 3: ; break;
                case 4: ; break;
            }
        }
        while (cch != 9);
    }

    public void ShowProf ()
    {
        int i;
        System.out.println("*** ΛΙΣΤΑ ΚΑΘΗΓΗΤΩΝ ***");
        for (i = 0; i < 10; i++)
            if (Profs[i] != null)
                System.out.printf("%2d   %-20s  %-20s  %s\n", i + 1, Profs[i].getPID(), Profs[i].getFullName(), (Profs[i].getTeaches() == null) ? "---" : Profs[i].getTeaches().getName());
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
