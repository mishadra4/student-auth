package com.md.sa.facade.converter;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.md.sa.facade.dto.LectureAchievementStudentReportData;
import com.md.sa.model.LectureAchievementStudentReport;
import com.md.sa.model.User;

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

        User user = entity.getUser();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());

        Optional.ofNullable(user.getGroup()).ifPresent(group -> dto.setGroupName(group.getName()));

        dto.setFirstName(entity.getUser().getFirstName());
        dto.setPresent(Objects.nonNull(entity.getPresent()) ? entity.getPresent() : false);

        return dto;
    }
}
