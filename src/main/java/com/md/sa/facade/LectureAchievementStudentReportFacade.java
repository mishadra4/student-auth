package com.md.sa.facade;

import java.util.List;

import com.md.sa.facade.dto.LectureAchievementStudentReportData;

public interface LectureAchievementStudentReportFacade {

    List<LectureAchievementStudentReportData> findAllByLecture(Integer lectureId);

    LectureAchievementStudentReportData update(Integer lectureId, Integer achievementId, LectureAchievementStudentReportData record);

}
