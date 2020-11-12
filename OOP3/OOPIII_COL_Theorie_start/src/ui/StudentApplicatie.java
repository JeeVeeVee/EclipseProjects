package ui;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import domein.Student;
import sun.text.normalizer.Trie2.ValueMapper;

public class StudentApplicatie
{

    public static void main(String[] args)
    {
        List<Student> lijstStudenten = new ArrayList<>();

        
        lijstStudenten.add(new Student(20132566,"Janssens","Wendy","Eke"));
        lijstStudenten.add(new Student(20132567,"Janssens","Hans","Oudenaarde"));
        lijstStudenten.add(new Student(20132563,"Janssens","Jan","Gent"));
        lijstStudenten.add(new Student(20132564,"Karels","Matt","Gent"));
        lijstStudenten.add(new Student(20132565,"Fransen","Luc","Gent"));
         
        System.out.println(lijstStudenten);
        
        
    }
}
