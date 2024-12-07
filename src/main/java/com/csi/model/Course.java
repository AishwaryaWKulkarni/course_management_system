package com.csi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CourseInfo")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseid")
    private int id;

    @NotNull
    @Column(name = "coursename")
    private String courseName;

    @NotNull
    @Column(name = "coursefee")
    private double fee;

    @NotNull
    @Column(name = "courseinstructor")
    private String instructor;




}
