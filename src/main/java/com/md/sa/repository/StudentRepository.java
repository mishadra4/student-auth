package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.md.sa.model.Lecture;
import com.md.sa.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
