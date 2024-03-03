package com.yin.srms.controller;

import com.yin.srms.model.Course;
import com.yin.srms.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

    @PostMapping(value="/course",consumes = MediaType.APPLICATION_JSON_VALUE)
    Course createNewCourse(@RequestBody Course newCourse){
        return courseRepo.save(newCourse);
    }

    @GetMapping("/courses")
    List<Course> getAllCourses(){
        return  courseRepo.findAll();
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        if (!courseRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course with id " + id + " cannot be found.");
        }
        courseRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Course with id " + id + " has been deleted successfully.");
    }

}
