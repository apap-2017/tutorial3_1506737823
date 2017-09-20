package com.apap.tutorial3.demo.Service;

import com.apap.tutorial3.demo.Model.StudentModel;

import java.util.List;

public interface StudentService {
    StudentModel selectStudent(String npm);

    List<StudentModel> selectAllStudents();

    void addStudent(StudentModel student);

    void deleteStudent(StudentModel student);
}
