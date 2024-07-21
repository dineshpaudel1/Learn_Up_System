package com.Learn_Up.System.Services.CourseServices;

import com.Learn_Up.System.Entities.CourseEntity.CourseEntity;
import com.Learn_Up.System.Models.CourseModel.Course;
import com.Learn_Up.System.Models.CourseModel.CourseResponse;
import com.Learn_Up.System.Models.UsersModel.UsersResponse;
import com.Learn_Up.System.Repositories.CourseRepository.CourseRepository;
import org.springframework.beans.BeanUtils;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Override
    public ResponseEntity<List<CourseResponse>>getCourse(){
        List<CourseEntity> courseEntity = courseRepository.findAll();
        List<CourseResponse> courseList = courseEntity.stream()
                .map(courseData ->{
                    CourseResponse courseResponse = new CourseResponse();
                    BeanUtils.copyProperties(courseData, courseResponse);
                    return courseResponse;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String>deleteCourse(Long courseId){
        try{
            if (courseRepository.existsById(courseId)){
                courseRepository.deleteById(courseId);
                return new ResponseEntity<>("courser deleted successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String>updateCourse(Long courseId, Course course, MultipartFile file){
        try{
            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
            if(optionalCourse.isPresent()){
                CourseEntity existingCourse = optionalCourse.get();
                existingCourse.setCourseTitle(course.getCourseTitle());
                existingCourse.setCourseDescription(course.getCourseDescription());
                existingCourse.setCategory(course.getCategory());
                existingCourse.setPrice(course.getPrice());

                if(file != null){
                    String fileName = file.getOriginalFilename();
                    String filepath = path + File.separator + fileName;

                    Files.copy(file.getInputStream(), Paths.get(filepath));
                    existingCourse.setThumbnail("courses/photo?fileName="+fileName);
                }
                courseRepository.save(existingCourse);
                return new ResponseEntity<>("Course updated sucessfully",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Course Not found: "+courseId, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error while updating course: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


