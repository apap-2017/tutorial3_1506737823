package com.apap.tutorial3.demo.Controller;

import com.apap.tutorial3.demo.Model.StudentModel;
import com.apap.tutorial3.demo.Service.InMemoryStudentService;
import com.apap.tutorial3.demo.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(){
        studentService = new InMemoryStudentService();
    }

    @RequestMapping("/student/add")
    public String add(@RequestParam(value = "npm", required = true) String npm,
                      @RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "gpa", required = true) double gpa){
        StudentModel student = new StudentModel(npm, name, gpa);
        studentService.addStudent(student);
        return "add";
    }

    @RequestMapping("/student/view")
    public String view (Model model, @RequestParam(value = "npm", required = false) String npm){
        if(npm == null){
            return "viewfail";
        }

        StudentModel student = studentService.selectStudent(npm);
        model.addAttribute("student", student);
        return "view";
    }

    @RequestMapping("/student/viewall")
    public String viewAll(Model model){
        List<StudentModel> students = studentService.selectAllStudents();
        model.addAttribute("students", students);
        return "viewAll";
    }

    @RequestMapping("/student/view/{npm}")
    public String view (Model model, @PathVariable Optional<String> npm){

        StudentModel student = studentService.selectStudent(npm.get());

        if(student == null){
            return "viewfail";
        }
        model.addAttribute("student", student);
        return "view";
    }

    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable Optional<String> npm){
        StudentModel student = studentService.selectStudent(npm.get());

        if(student == null){
            model.addAttribute("output", "Student tidak ada");
        }else{
            studentService.deleteStudent(student);
            model.addAttribute("output", "Student telah dihapus");
        }

        return "delete";
    }

    @RequestMapping("/student/delete")
    public String delete (Model model){
        model.addAttribute("output", "Tolong masukkan parameter");
        return "delete";
    }
}
