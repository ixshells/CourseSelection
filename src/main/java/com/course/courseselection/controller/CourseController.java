package com.course.courseselection.controller;import com.course.courseselection.command.CourseCommand;import com.course.courseselection.command.CourseListResponse;import com.course.courseselection.command.CourseSelectionCommand;import com.course.courseselection.service.CourseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import static org.springframework.web.bind.annotation.RequestMethod.GET;import static org.springframework.web.bind.annotation.RequestMethod.POST;@RestController@RequestMapping("/course")public class CourseController {    @Autowired    private CourseService courseService;    @RequestMapping(method = POST)    @PreAuthorize("hasRole('STUDENT')")    public void addCourse(@RequestBody  CourseCommand courseCommand) {        courseService.add(courseCommand);    }    @RequestMapping(value = "/select", method = POST)    @ResponseStatus(value = HttpStatus.CREATED)    public void selectCourse(@RequestBody CourseSelectionCommand command) {        courseService.select(command);    }    @RequestMapping(method = GET)    public CourseListResponse getCourse() {        return courseService.query();    }}