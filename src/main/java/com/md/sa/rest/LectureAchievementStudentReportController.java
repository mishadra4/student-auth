package com.md.sa.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.sa.facade.LectureAchievementStudentReportFacade;
import com.md.sa.facade.dto.LectureAchievementStudentReportData;

@RestController
@RequestMapping("/lectures/{lecture_id}/achievements")
public class LectureAchievementStudentReportController {

    private final LectureAchievementStudentReportFacade lectureAchievementStudentReportFacade;

    public LectureAchievementStudentReportController(LectureAchievementStudentReportFacade facade) {
        this.lectureAchievementStudentReportFacade = facade;
    }

    @GetMapping
    public List<LectureAchievementStudentReportData> findAllByLecture(@PathVariable("lecture_id") Integer lectureId) {
        return lectureAchievementStudentReportFacade.findAllByLecture(lectureId);
    }

    @PutMapping("/{achievement_id}")
    public LectureAchievementStudentReportData update(@PathVariable("lecture_id") Integer lectureId,
                                                      @PathVariable("achievement_id") Integer achievementId,
                                                      @RequestBody LectureAchievementStudentReportData reportRecord) {
        return lectureAchievementStudentReportFacade.update(lectureId, achievementId, reportRecord);
    }
}
