package com.Learn_Up.System.Repositories.CourseRepository;

import com.Learn_Up.System.Entities.CourseEntity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
