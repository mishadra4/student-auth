package com.md.sa.facade.converter;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.md.sa.facade.dto.LabAchievementStudentReportData;
import com.md.sa.model.LabAchievementStudentReport;
import com.md.sa.model.User;

@Component
public class LabAchievementStudentReportConverter implements GenericConverter<LabAchievementStudentReport, LabAchievementStudentReportData>{

    @Override
    public LabAchievementStudentReport convertToEntity(LabAchievementStudentReportData dto) {
        throw new UnsupportedOperationException("No operation");
    }

    @Override
    public LabAchievementStudentReportData convertToDTO(LabAchievementStudentReport entity) {
        LabAchievementStudentReportData dto = new LabAchievementStudentReportData();
        dto.setId(entity.getId());
        dto.setGrade(entity.getGrade());

        User user = entity.getUser();
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());

        Optional.ofNullable(user.getGroup()).ifPresent(group -> dto.setGroupName(group.getName()));

        dto.setPresent(Objects.nonNull(entity.getPresent()) ? entity.getPresent() : false);

        return dto;
    }
}
