package com.course.courseselection.command;import java.util.List;public class CourseListResponse {    private List<CourseResponse> courses;    public List<CourseResponse> getCourses() {        return courses;    }    public void setCourses(List<CourseResponse> courses) {        this.courses = courses;    }}