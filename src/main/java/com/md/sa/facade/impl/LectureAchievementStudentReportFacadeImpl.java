package com.md.sa.facade.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.md.sa.facade.LectureAchievementStudentReportFacade;
import com.md.sa.facade.converter.LectureAchievementStudentReportConverter;
import com.md.sa.facade.dto.LectureAchievementStudentReportData;
import com.md.sa.model.LectureAchievementStudentReport;
import com.md.sa.repository.LectureAchievementStudentReportRepository;


@Component
public class LectureAchievementStudentReportFacadeImpl implements LectureAchievementStudentReportFacade {

    private final LectureAchievementStudentReportRepository repository;

    private final LectureAchievementStudentReportConverter converter;

    public LectureAchievementStudentReportFacadeImpl(LectureAchievementStudentReportRepository repository,
                                                     LectureAchievementStudentReportConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<LectureAchievementStudentReportData> findAllByLecture(Integer lectureId) {
        return repository.findAllByLectureId(lectureId)
                .stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LectureAchievementStudentReportData update(Integer lectureId, Integer achievementId, LectureAchievementStudentReportData record) {
        Optional<LectureAchievementStudentReport> lectureAchievementStudentReportOpt = repository.findById(achievementId);
        if (lectureAchievementStudentReportOpt.isPresent()) {
            LectureAchievementStudentReport lectureAchievementStudentReport = lectureAchievementStudentReportOpt.get();
            lectureAchievementStudentReport.setGrade(record.getGrade());
            lectureAchievementStudentReport.setPresent(record.getPresent());

            lectureAchievementStudentReport = repository.save(lectureAchievementStudentReport);

            return converter.convertToDTO(lectureAchievementStudentReport);
        } else {
            throw new RuntimeException("not found");
        }
    }
}
