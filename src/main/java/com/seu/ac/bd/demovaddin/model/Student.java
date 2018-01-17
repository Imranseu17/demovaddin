package com.seu.ac.bd.demovaddin.model;

import com.vaadin.ui.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.sql.Blob;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String studentID;
    private String studentName;
    private double cgpa;
    @Lob
   private Blob image;
}
