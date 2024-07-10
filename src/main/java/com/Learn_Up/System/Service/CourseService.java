package com.Learn_Up.System.Service;

import com.Learn_Up.System.Entity.Course;
import com.Learn_Up.System.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setInstructor(updatedCourse.getInstructor());
            course.setPrice(updatedCourse.getPrice());
            course.setRating(updatedCourse.getRating());
            return courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("Course not found with id: " + id);
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
