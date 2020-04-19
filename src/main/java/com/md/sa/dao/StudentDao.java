package com.md.sa.dao;

import com.md.sa.model.Student;

import java.util.List;

public interface StudentDao {
    boolean enrollStudent(String username);

    Student getStudent(Integer id);

    Student getStudent(String username);

    List<Student> getAllStudents();

    void save(Student student);

}
