package com.Learn_Up.System.Repository;


import com.Learn_Up.System.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
