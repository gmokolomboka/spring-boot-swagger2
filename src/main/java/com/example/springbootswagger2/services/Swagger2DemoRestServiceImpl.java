package com.example.springbootswagger2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springbootswagger2.model.Student;
import org.springframework.stereotype.Service;

@Service
public class Swagger2DemoRestServiceImpl implements Swagger2DemoRestService
{
    
    List<Student> students = new ArrayList<Student>();
    {
        students.add(new Student("Yvon", "IV", "USA"));
        students.add(new Student("Sylvain", "V", "ITALY"));
        students.add(new Student("Sacha", "III", "USA"));
        students.add(new Student("Luc", "VI", "FRANCE"));
    }

    
    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(String name) {
        return students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
    }

    public List<Student> getStudentByCountry(String country) {
        System.out.println("Searching Student in country : " + country);
        List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        System.out.println(studentsByCountry);
        return studentsByCountry;
    }

    public List<Student> getStudentByClass(String cls) {
        return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
    }

}
