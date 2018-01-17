package com.seu.ac.bd.demovaddin.ui;

import com.seu.ac.bd.demovaddin.model.ImageUpload;
import com.seu.ac.bd.demovaddin.model.Student;
import com.seu.ac.bd.demovaddin.service.StudentService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ImageRenderer;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@SpringUI
public class DesignUI extends UI {
    private VerticalLayout verticalLayout;
    private Label label;
    private Button button;
    private TextField studentIDField;
    private TextField studentNameField;
    private TextField studentcgpaField;
    private Button addStudent;
    Grid<Student> studentGrid;
    List<Student> studentList;


    @Autowired
    StudentService studentService;
    Image image = new Image();




    @Override
    protected void init(VaadinRequest vaadinRequest) {
        verticalLayout = new VerticalLayout();
        this.setContent(verticalLayout);
        label = new Label("Hello vaddin");
        verticalLayout.addComponent(label);
        button = new Button("click");
        button.addClickListener(clickEvent -> label.setValue("Clicked"));
        verticalLayout.addComponent(button);
        studentList = studentService.getAllstudents();
        FormLayout formLayout = new FormLayout();
        verticalLayout.addComponent(formLayout);
        studentIDField = new TextField("StudentID");
        studentNameField = new TextField("StudentName");
        studentcgpaField = new TextField("Studentcgpa");
        image.setWidth("128px");
        image.setHeight("128px");
        ImageUpload imageUpload = new ImageUpload(image);
        Upload studentImageFiled = new Upload();
        studentImageFiled.setCaption("Browse");
        studentImageFiled.setButtonCaption("Send");
        studentImageFiled.addSucceededListener(imageUpload);
        studentImageFiled.setReceiver(imageUpload);
        addStudent = new Button("Add");
        formLayout.setCaption("Add a new Student");
        formLayout.addComponent(studentIDField);
        formLayout.addComponent(studentNameField);
        formLayout.addComponent(studentcgpaField);
        formLayout.addComponent(studentImageFiled);
        formLayout.addComponent(image);
        formLayout.addComponent(addStudent);
        addStudent.addClickListener(clickEvent -> {
            String studentID = studentIDField.getValue();
            String studentName = studentNameField.getValue();
            double studentCgpa = Double.parseDouble(studentcgpaField.getValue());
            Blob studentImage = null;// = (Blob) studentImageFiled.getReceiver();
            try {
                byte[] input = new byte[(int) studentImageFiled.getBytesRead()];
                Upload.Receiver receiver = studentImageFiled.getReceiver();
                ((Blob) studentImageFiled.getReceiver()).getBinaryStream().read(input);
                studentImage = new SerialBlob(input);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //((Blob) studentImageFiled.getReceiver()).getBinaryStream()
            studentService.addStudent(studentID,studentName,studentCgpa,studentImage);
            studentList.add(new Student(studentID,studentName,studentCgpa,studentImage));
            studentGrid.clearSortOrder();
            studentIDField.clear();
            studentNameField.clear();
            studentcgpaField.clear();
            Notification.show("Added "+studentName,Notification.Type.TRAY_NOTIFICATION);
        });

        studentGrid = new Grid<>();
        studentGrid.setItems(studentList);
        verticalLayout.addComponent(studentGrid);

        studentGrid.addColumn(Student::getStudentID).setCaption("Student ID");
        studentGrid.addColumn(Student::getStudentName).setCaption("Student Name");
        studentGrid.addColumn(Student::getCgpa).setCaption("Student CGPA");
        studentGrid.addColumn(Student::getImage).setCaption("Image").setRenderer(new ImageRenderer());




    }


}
