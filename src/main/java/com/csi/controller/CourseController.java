package com.csi.controller;


import com.csi.exception.RecordNotFoundException;
import com.csi.model.Course;
import com.csi.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courseinfo")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @PostMapping("/add")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.addCourse(course));
    }

    @GetMapping("viewAll")
    public ResponseEntity<List<Course>> viewAll(){
        return ResponseEntity.ok(courseService.viewAll());
    }

    @GetMapping("viewById/{courseid}")
    public ResponseEntity<Optional<Course>> viewById(@PathVariable int courseid){

        return ResponseEntity.ok(courseService.viewById(courseid));
    }

    @PutMapping("/update/{courseid}")
    public ResponseEntity<Course> updateCourse(@PathVariable int courseid, @RequestBody Course course){

       Course course1=courseService.viewById(courseid).orElseThrow(()->new RecordNotFoundException("Course ID does not exist"));

       course1.setCourseName(course.getCourseName());
       course1.setFee(course.getFee());
       course1.setInstructor(course.getInstructor());

      return ResponseEntity.ok(courseService.updateCourse(course1));
    }

    @GetMapping("filterByCourseName/{courseName}")
    public ResponseEntity<List<Course>> filterByCourseName(@PathVariable String courseName){

        return ResponseEntity.ok(courseService.viewAll().stream().filter(c->c.getCourseName().equals(courseName)).toList());
    }

    @GetMapping("sortByCourseFee")
    public ResponseEntity<List<Course>> sortByCourseFee(){

        return ResponseEntity.ok(courseService.viewAll().stream().sorted(Comparator.comparingDouble(Course::getFee)).toList());
    }


    @GetMapping("fetchByInstructorName/{instructorName}")
    public ResponseEntity<List<Course>> filterByInstructorName(@PathVariable String instructorName){

        return ResponseEntity.ok(courseService.viewAll().stream().filter(e->e.getInstructor().equals(instructorName)).toList());
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id){
       courseService.deleteCourse(id);

        return  ResponseEntity.ok("Data Deleted SuccessFully");
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAll(){
        courseService.deleteAllCourse();

        return ResponseEntity.ok("All Data Deleted Successfully");
    }


}
