package com.Learn_Up.System.Repository;


import com.Learn_Up.System.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
