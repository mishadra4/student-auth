package com.md.sa.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.sa.facade.LabAchievementStudentReportFacade;
import com.md.sa.facade.dto.LabAchievementStudentReportData;

@RestController
@RequestMapping("/labs/{lab_id}/achievements")
public class LabAchievementStudentReportController {

    private final LabAchievementStudentReportFacade labAchievementStudentReportFacade;

    public LabAchievementStudentReportController(LabAchievementStudentReportFacade facade) {
        this.labAchievementStudentReportFacade = facade;
    }

    @GetMapping
    public List<LabAchievementStudentReportData> findAllByLecture(@PathVariable("lab_id") Integer labId) {
        return labAchievementStudentReportFacade.findAllByLab(labId);
    }

    @PutMapping("/{achievement_id}")
    public LabAchievementStudentReportData update(@PathVariable("lecture_id") Integer lectureId,
                                                  @PathVariable("achievement_id") Integer achievementId,
                                                  @RequestBody LabAchievementStudentReportData reportRecord) {
        return labAchievementStudentReportFacade.update(lectureId, achievementId, reportRecord);
    }
}
