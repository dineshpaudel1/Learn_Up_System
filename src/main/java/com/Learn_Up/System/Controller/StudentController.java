package com.Learn_Up.System.Controller;

import com.Learn_Up.System.ApiResponse;
import com.Learn_Up.System.Entity.Student;
import com.Learn_Up.System.Service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private Studentservice studentservice;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerStudent(@RequestBody Student student) {
        Student saveStudent = studentservice.saveStudent(student);
        if(saveStudent != null){
            ApiResponse response = new ApiResponse("Student registered successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else{
            ApiResponse response = new ApiResponse("unsussful");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping
    public List<Student>getAllStudents(){
        return studentservice.getAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentservice.getStudentById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentservice.deleteStudentById(id);

    }

    @PutMapping("{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student student){
        student.setId(id);
        return studentservice.saveStudent(student);
    }


}
