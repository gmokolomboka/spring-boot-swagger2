package com.example.springbootswagger2.services;

import java.util.List;

import com.example.springbootswagger2.model.Student;

public interface Swagger2DemoRestService
{
    public List<Student> getStudents();
    public Student getStudent(String name) ;
    public List<Student> getStudentByCountry(String country);
    public List<Student> getStudentByClass(String cls);
    
}
