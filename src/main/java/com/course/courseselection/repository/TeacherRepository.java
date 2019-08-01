package com.course.courseselection.repository;

import com.course.courseselection.entity.TeacherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherData, String> {
    TeacherData findByName(String name);
}
