package ui;

import domein.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentApplicatieMap
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
