package com.md.sa.facade.impl;

import com.md.sa.facade.SubjectFacade;
import com.md.sa.facade.converter.GenericConverter;
import com.md.sa.facade.dto.SubjectData;
import com.md.sa.model.Subject;
import com.md.sa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectFacadeImpl implements SubjectFacade {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GenericConverter<Subject, SubjectData> subjectConverter;

    @Override
    public List<SubjectData> getSubjects(String username) {
        return subjectService.getSubjects(username).stream()
                .map(subjectConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectData getSubject(int id) {
        return subjectConverter.convertToDTO(subjectService.getSubject(id));
    }

    @Override
    public SubjectData getSubject(String name) {
        return subjectConverter.convertToDTO(subjectService.getSubject(name));
    }
}
