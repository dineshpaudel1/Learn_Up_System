package com.Learn_Up.System.Services.CourseServices;

import com.Learn_Up.System.Models.CourseModel.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CourseServices {
    ResponseEntity<String>addCourse(Course course, MultipartFile file);
}
