package com.md.sa.facade.impl;

import com.md.sa.facade.LabFacade;
import com.md.sa.facade.converter.GenericConverter;
import com.md.sa.facade.dto.LabData;
import com.md.sa.facade.dto.LectureData;
import com.md.sa.model.Lab;
import com.md.sa.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LabFacadeImpl implements LabFacade {

    @Autowired
    private LabService labService;

    @Autowired
    private GenericConverter<Lab, LabData> labConverter;

    @Override
    public void createLab(LabData labData) {
        labService.saveLab(labConverter.convertToEntity(labData));
    }

    @Override
    public LabData getLab(final Integer labId) {
        return labConverter.convertToDTO(labService.getLab(labId));
    }

    @Override
    public List<LabData> getLabs(final String subjectId) {
        return labService.getLabs(subjectId).stream()
                .map(labConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
