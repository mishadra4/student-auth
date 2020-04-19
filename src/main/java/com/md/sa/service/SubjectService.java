package com.md.sa.service;


import com.md.sa.model.Subject;

import java.util.List;

public interface SubjectService {

    void save(Subject subject);

    void saveAll(List<Subject> subjects);

    Subject getSubject(final String name);

    Subject getSubject(final int id);

    List<Subject> getSubjects(final String username);
}
