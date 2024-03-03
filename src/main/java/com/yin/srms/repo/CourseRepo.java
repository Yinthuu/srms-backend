package com.yin.srms.repo;

import com.yin.srms.model.Course;
import com.yin.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
