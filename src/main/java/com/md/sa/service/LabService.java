package com.md.sa.service;

import com.md.sa.model.Lab;
import com.md.sa.model.Student;

import java.util.List;

public interface LabService {

    Lab getLab(Integer id);

    List<Lab> getLabs(String subjectId);

    void saveLab(Lab lab);

    void unEnrollStudent(int labId, Student student);

    void enrollStudent(int labId, Student student);
}
