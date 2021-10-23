package com.md.sa.dao;

import com.md.sa.model.Subject;

import java.util.List;

public interface SubjectDao {
    void save(Subject subject);

    Subject getSubject(String name);

    Subject getSubject(final int id);

    List<Subject> getSubjects(String username);
}
