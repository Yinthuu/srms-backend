package com.yin.srms.controller;

import com.yin.srms.model.Result;
import com.yin.srms.model.Student;
import com.yin.srms.repo.ResultRepo;
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
    @Autowired
    private ResultRepo resultRepo;


    @PostMapping(value="/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    Student newStudent(@RequestBody Student newStudent){
        return studentRepo.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getAllStudents(){
        return  studentRepo.findAll();
    }

    @DeleteMapping("/student/{id}")
    String deleteStudent(@PathVariable Long id){
        if(!studentRepo.existsById(id)){
            return  "Student with id "+id+" cannot be found.";
        }
        //In order to delete a student, delete the associated results first
        List<Result> results = resultRepo.findByStudentId(id);
        for (Result result : results) {
            resultRepo.delete(result);
        }

        studentRepo.deleteById(id);
        return  "Student with id "+id+" has been deleted success.";
    }


}
