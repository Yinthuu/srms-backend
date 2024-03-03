package com.yin.srms.repo;

import com.yin.srms.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Long> {

    @Query("SELECT r FROM Result r WHERE r.student.id = :studentId")
    List<Result> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT r FROM Result r WHERE r.course.id = :courseId")
    List<Result> findByCourseId(@Param("courseId") Long courseId);
}
