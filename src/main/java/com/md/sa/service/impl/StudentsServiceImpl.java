package com.md.sa.service.impl;


import com.md.sa.dao.StudentDao;
import com.md.sa.model.Student;
import com.md.sa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void studentRegister(Integer studentId) {
    }

    @Override
    public void save(Student student) {
        getStudentDao().save(student);
    }

    @Override
    public void saveAll(List<Student> students) {
        students.forEach(this::save);
    }

    @Override
    public Student getStudent(String username) {
        return studentDao.getStudent(username);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
