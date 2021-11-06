package com.md.sa.facade.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.md.sa.facade.LabAchievementStudentReportFacade;
import com.md.sa.facade.converter.LabAchievementStudentReportConverter;
import com.md.sa.facade.dto.LabAchievementStudentReportData;
import com.md.sa.model.LabAchievementStudentReport;
import com.md.sa.repository.LabAchievementStudentReportRepository;


@Component
public class LabAchievementStudentReportFacadeImpl implements LabAchievementStudentReportFacade {

    private final LabAchievementStudentReportRepository repository;
    private final LabAchievementStudentReportConverter converter;

    public LabAchievementStudentReportFacadeImpl(LabAchievementStudentReportRepository repository,
                                                 LabAchievementStudentReportConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabAchievementStudentReportData> findAllByLab(Integer labId) {
        return repository.findAllByLabId(labId)
                .stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LabAchievementStudentReportData update(Integer labId, Integer achievementId, LabAchievementStudentReportData record) {
        Optional<LabAchievementStudentReport> labAchievementStudentReportOpt = repository.findById(achievementId);
        if (labAchievementStudentReportOpt.isPresent()) {
            LabAchievementStudentReport labAchievementStudentReport = labAchievementStudentReportOpt.get();
            labAchievementStudentReport.setGrade(record.getGrade());
            labAchievementStudentReport.setPresent(record.getPresent());

            labAchievementStudentReport = repository.save(labAchievementStudentReport);

            return converter.convertToDTO(labAchievementStudentReport);
        } else {
            throw new RuntimeException("not found");
        }
    }
}
