package com.course.courseselection.translator;

import com.course.courseselection.entity.StudentData;
import com.course.courseselection.command.Student;
import org.junit.Assert;
import org.junit.Test;

public class StudentTranslatorTest {

    private StudentTranslator studentTranslator = new StudentTranslator();

    @Test
    public void should_translate_student_to_studentdata_correctly() {
        String name = "name";
        String password = "password";
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        StudentData studentData = studentTranslator.translate(student);
        Assert.assertEquals(studentData.getName(), student.getName());
        Assert.assertEquals(student.getPassword(), student.getPassword());
    }
}