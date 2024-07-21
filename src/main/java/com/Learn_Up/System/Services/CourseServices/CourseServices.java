package com.Learn_Up.System.Services.CourseServices;

import com.Learn_Up.System.Models.CourseModel.Course;
import com.Learn_Up.System.Models.CourseModel.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CourseServices {
    ResponseEntity<String>addCourse(Course course, MultipartFile file);
    ResponseEntity<List<CourseResponse>>getCourse();
    ResponseEntity<String>deleteCourse(Long courseId);
    ResponseEntity<String>updateCourse(Long courseId, Course course, MultipartFile file);
}
