package com.Learn_Up.System.Controllers.CourseController;

import com.Learn_Up.System.Models.CourseModel.Course;
import com.Learn_Up.System.Services.CourseServices.CourseServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseControllers {

    @Autowired
    private ObjectMapper mapper;

    @Value("$project.image")
    private String path;

    @Autowired
    private CourseServices courseServices;

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestParam("course")String data, @RequestParam("file")MultipartFile file)throws IOException {
        Course course = mapper.readValue(data, Course.class);
        return courseServices.addCourse(course, file);
    }
}
