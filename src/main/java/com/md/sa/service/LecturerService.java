package com.md.sa.service;

import com.md.sa.model.Lecturer;

import java.util.List;

public interface LecturerService {

    Lecturer getLecturerByUsername(String username);

    void save(Lecturer lecturer);

    void saveAll(List<Lecturer> lecturers);
}
