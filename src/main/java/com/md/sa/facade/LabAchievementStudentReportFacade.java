package com.md.sa.facade;

import java.util.List;

import com.md.sa.facade.dto.LabAchievementStudentReportData;

public interface LabAchievementStudentReportFacade {

    List<LabAchievementStudentReportData> findAllByLab(Integer labId);

    LabAchievementStudentReportData update(Integer labId, Integer achievementId, LabAchievementStudentReportData record);
}
