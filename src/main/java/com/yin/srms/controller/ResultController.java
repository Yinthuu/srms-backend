package com.yin.srms.controller;

import com.yin.srms.model.Course;
import com.yin.srms.model.Result;
import com.yin.srms.model.Student;
import com.yin.srms.repo.CourseRepo;
import com.yin.srms.repo.ResultRepo;
import com.yin.srms.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class ResultController {

    @Autowired
    private ResultRepo resultRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;

    @PostMapping("/result")
    public ResponseEntity<?> createNewResult(@RequestBody Result result) {
        try {
            Optional<Student> optionalStudent = studentRepo.findById(result.getStudent().getId());
            if (optionalStudent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Student with ID " + result.getStudent().getId() + " not found\"}");
            }

            Optional<Course> optionalCourse = courseRepo.findById(result.getCourse().getId());
            if (optionalCourse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Course with ID " + result.getCourse().getId() + " not found\"}");
            }

            result.setStudent(optionalStudent.get());
            result.setCourse(optionalCourse.get());

            resultRepo.save(result);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Result is successfully created!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/results")
    List<Result> getAllResults(){
        return  resultRepo.findAll();
    }

}
