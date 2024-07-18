package com.Learn_Up.System.Services.CourseServices;

import com.Learn_Up.System.Entities.CourseEntity.CourseEntity;
import com.Learn_Up.System.Models.CourseModel.Course;
import com.Learn_Up.System.Repositories.CourseRepository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CourseServiceImplementation implements CourseServices{
    @Autowired
    private CourseRepository courseRepository;

    @Value("${project.image}")
    private String path;

    public ResponseEntity<String>addCourse(Course course, MultipartFile file){
        try{
            String fileNmae = file.getOriginalFilename();
            String filepath = path + File.separator + fileNmae;

            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(filepath));

            CourseEntity courseEntity = CourseEntity.builder().courseTitle(course.getCourseTitle())
                    .courseDescription(course.getCourseDescription())
                    .category(course.getCategory())
                    .price(course.getPrice())
                    .thumbnail("courses/photo?fileName=" + fileNmae).build();
            courseRepository.save(courseEntity);
            return new ResponseEntity<>("Course added successfully", HttpStatus.OK);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
