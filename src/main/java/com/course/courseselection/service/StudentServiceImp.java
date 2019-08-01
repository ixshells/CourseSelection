package com.course.courseselection.service;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.model.Student;
import com.course.courseselection.model.StudentRequest;
import com.course.courseselection.repository.StudentRepository;
import com.course.courseselection.translator.StudentTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentTranslator studentTranslator;

    @Override
    public void add(StudentRequest request) {
        for(Student student: request.getStudents()) {
            StudentData studentData = studentTranslator.translate(student);
            studentRepository.save(studentData);
        }
    }
}
