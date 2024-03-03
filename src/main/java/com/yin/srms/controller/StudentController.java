package com.yin.srms.controller;

import com.yin.srms.model.Student;
import com.yin.srms.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping(value="/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    Student newStudent(@RequestBody Student newStudent){
        return studentRepo.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getAllStudents(){
        return  studentRepo.findAll();
    }

}
