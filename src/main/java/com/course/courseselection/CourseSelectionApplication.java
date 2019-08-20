package com.course.courseselection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CourseSelectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseSelectionApplication.class, args);
	}

}
