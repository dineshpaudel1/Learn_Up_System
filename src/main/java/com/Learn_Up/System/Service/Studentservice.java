package com.Learn_Up.System.Service;

import com.Learn_Up.System.Entity.Student;
import com.Learn_Up.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Studentservice {
    @Autowired
    private StudentRepository studentRepository;
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public List<Student>getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }
}
