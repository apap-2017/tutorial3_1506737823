package com.apap.tutorial3.demo.Service;

import com.apap.tutorial3.demo.Model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStudentService implements StudentService {
    private static List<StudentModel> studentList = new ArrayList<StudentModel>();

    @Override
    public StudentModel selectStudent(String npm){

        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getNpm().equals(npm)){
                return studentList.get(i);
            }
        }

        return null;
    }

    @Override
    public List<StudentModel> selectAllStudents(){
        return studentList;
    }

    @Override
    public void addStudent(StudentModel student){
        studentList.add(student);
    }

    @Override
    public void deleteStudent(StudentModel student){
        studentList.remove(student);
    }
}
