package com.example.springbootswagger2;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springbootswagger2.model.Student;

@RunWith(SpringRunner.class)
public class SpringBootSwagger2ApplicationTests {

    
    
    private List<Student> students;


    @Test
    public void getStudent() {
        
    }

    @Test
    public void getStudentByCountry() {
        
        Assert.assertTrue( students == null );
    }

    public void getStudentByClass() {
           Assert.assertFalse( students != null );
    }
}
