package com.seu.ac.bd.demovaddin.repository;

import com.seu.ac.bd.demovaddin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Studentrepository extends JpaRepository<Student,String> {
   List<Student> findStudentByStudentName(String studentName);
}
