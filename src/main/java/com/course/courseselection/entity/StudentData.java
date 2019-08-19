package com.course.courseselection.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Student")
@Data
public class StudentData extends UserData {

    @Column(name = "studentId")
    private String studentId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses",
            joinColumns = {@JoinColumn(name = "studentId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "courseId", referencedColumnName = "courseId")})
    private List<Course> courses;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
