package com.md.sa.facade;

import com.md.sa.facade.dto.SubjectData;

import java.util.List;

public interface SubjectFacade {

    List<SubjectData> getSubjects(String username);

    SubjectData getSubject(int id);

    SubjectData getSubject(String name);

}
