package com.md.sa.dao;

import com.md.sa.model.Lab;
import com.md.sa.model.Lecture;

import java.util.List;

public interface LabDao {
    void createLab();

    Lab getLab(Integer id);

    void saveLab(Lab lab);

    List<Lab> getLabs(String subjectId);
}
