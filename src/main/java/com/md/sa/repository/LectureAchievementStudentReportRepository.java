package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.md.sa.model.LectureAchievementStudentReport;

@Repository
public interface LectureAchievementStudentReportRepository extends JpaRepository<LectureAchievementStudentReport, Integer> {

    @Query("SELECT r FROM LectureAchievementStudentReport r WHERE r.lecture.lectureId = :lectureId")
    List<LectureAchievementStudentReport> findAllByLectureId(@Param("lectureId") Integer lectureId);
}
