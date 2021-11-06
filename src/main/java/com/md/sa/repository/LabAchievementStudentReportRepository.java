package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.md.sa.model.LabAchievementStudentReport;

@Repository
public interface LabAchievementStudentReportRepository extends JpaRepository<LabAchievementStudentReport, Integer> {

    @Query("SELECT r FROM LabAchievementStudentReport r WHERE r.lab.labId = :labId")
    List<LabAchievementStudentReport> findAllByLabId(@Param("labId") Integer labId);
}
