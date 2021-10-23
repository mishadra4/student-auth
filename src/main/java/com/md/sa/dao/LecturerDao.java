package com.md.sa.dao;

import com.md.sa.model.Lecturer;

import java.util.List;

public interface LecturerDao {

    void save(Lecturer lecturer);

    Lecturer getLecturer(String username);

    List<Lecturer> getLecturers();
}
