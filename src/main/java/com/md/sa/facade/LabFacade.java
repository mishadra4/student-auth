package com.md.sa.facade;

import com.md.sa.facade.dto.LabData;

import java.util.List;

public interface LabFacade {
    List<LabData> getLabs(String subjectId);

    LabData getLab(Integer labId);

    void createLab(LabData labData);
}
