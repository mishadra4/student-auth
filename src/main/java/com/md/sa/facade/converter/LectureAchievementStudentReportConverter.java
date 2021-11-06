package com.md.sa.facade.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.md.sa.facade.dto.LectureAchievementStudentReportData;
import com.md.sa.model.LectureAchievementStudentReport;

@Component
public class LectureAchievementStudentReportConverter
        implements GenericConverter<LectureAchievementStudentReport, LectureAchievementStudentReportData> {

    @Override
    public LectureAchievementStudentReport convertToEntity(LectureAchievementStudentReportData dto) {
        throw new UnsupportedOperationException("No operation");
    }

    @Override
    public LectureAchievementStudentReportData convertToDTO(LectureAchievementStudentReport entity) {
        LectureAchievementStudentReportData dto = new LectureAchievementStudentReportData();
        dto.setId(entity.getId());
        dto.setGrade(entity.getGrade());
        dto.setFullName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());
        dto.setPresent(Objects.nonNull(entity.getPresent()) ? entity.getPresent() : false);

        return dto;
    }
}
