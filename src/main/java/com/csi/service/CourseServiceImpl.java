package com.csi.service;

import com.csi.model.Course;
import com.csi.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl {

    @Autowired
    private CourseRepo courseRepoImpl;


    public Course addCourse(Course course){
        return courseRepoImpl.save(course);
    }

    @Cacheable(value = "id")
    public Optional<Course> viewById(int id){
        return courseRepoImpl.findById(id);
    }

    public List<Course> viewAll(){
        return courseRepoImpl.findAll();
    }

    public Course updateCourse(Course course){
        return courseRepoImpl.save(course);
    }

    public void deleteCourse(int courseId){

        courseRepoImpl.deleteById(courseId);

    }

    public void deleteAllCourse(){
        courseRepoImpl.deleteAll();
    }




}
