package com.example.hibernate;

public class MainApp {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        // Create and save student
        Student student = new Student("John Doe", 22);
        studentDAO.saveStudent(student);

        // Read student
        Student retrievedStudent = studentDAO.getStudent(student.getId());
        System.out.println("Retrieved Student: " + retrievedStudent.getName() + ", Age: " + retrievedStudent.getAge());

        // Update student
        retrievedStudent.setAge(23);
        studentDAO.updateStudent(retrievedStudent);

        // Delete student
        studentDAO.deleteStudent(retrievedStudent.getId());
    }
}
