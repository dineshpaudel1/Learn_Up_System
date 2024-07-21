package com.Learn_Up.System.Controllers.CourseController;

import com.Learn_Up.System.Models.CourseModel.Course;
import com.Learn_Up.System.Models.CourseModel.CourseResponse;
import com.Learn_Up.System.Repositories.CourseRepository.CourseRepository;
import com.Learn_Up.System.Services.CourseServices.CourseServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestParam("course")String data, @RequestParam("file")MultipartFile file)throws IOException {
        Course course = mapper.readValue(data, Course.class);
        return courseServices.addCourse(course, file);
    }
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourse(){
        return courseServices.getCourse();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCourse(@PathVariable Long id) throws IOException {
        return courseServices.deleteCourse(id);
    }
    @PostMapping("/{courseId}")
    public ResponseEntity<String>updateCoursse(@PathVariable Long courseId, @RequestParam("courses")String data,@RequestParam(value="file",required=false)MultipartFile file) throws IOException{
        Course course = mapper.readValue(data, Course.class);
        return courseServices.updateCourse(courseId,course,file);
    }
}
