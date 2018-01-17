package com.seu.ac.bd.demovaddin.service;

import com.seu.ac.bd.demovaddin.model.Student;
import com.seu.ac.bd.demovaddin.repository.Studentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Blob;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private Studentrepository studentrepository;


    public List<Student> getAllstudents(){
        return studentrepository.findAll();
    }

    public Student addStudent(String studentID, String studentName, double studentcgpa,Blob studentImage ){
        Student student = new Student(studentID,studentName,studentcgpa,studentImage);
        return studentrepository.save(student);
    }


}
