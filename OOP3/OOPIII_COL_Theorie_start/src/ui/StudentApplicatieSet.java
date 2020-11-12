package ui;

import domein.Student;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class StudentApplicatieSet
{
    public static void main(String[] args)
    {
        List<Student> lijstStudenten = new ArrayList<>();
        
        lijstStudenten.add(new Student(20132566,"Janssens","Wendy","Eke"));
        lijstStudenten.add(new Student(20132567,"Janssens","Hans","Oudenaarde"));
        lijstStudenten.add(new Student(20132563,"Janssens","Jan","Gent"));
        lijstStudenten.add(new Student(20132564,"Karels","Matt","Gent"));
        lijstStudenten.add(new Student(20132565,"Fransen","Luc","Gent"));
        lijstStudenten.add(new Student(20132566,"Janssens","Wendy","Eke"));
        lijstStudenten.add(new Student(20132567,"Janssens","Hans","Oudenaarde"));
        lijstStudenten.add(new Student(20132563,"Janssens","Jan","Gent"));
        lijstStudenten.add(new Student(20132564,"Karels","Matt","Gent"));
        lijstStudenten.add(new Student(20132565,"Fransen","Luc","Gent"));
         
        /*
         * 
         */
        Set<Student> set1 = new HashSet<>(lijstStudenten);
        System.out.println(set1);
        
        /*
        Set<Student> set2 = new TreeSet<>(lijstStudenten);
        System.out.println(set2);
      */
    }
}
