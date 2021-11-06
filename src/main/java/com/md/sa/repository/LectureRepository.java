package com.md.sa.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.md.sa.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query("SELECT l.lectureId, COUNT(l) FROM lecture AS l JOIN l.groups g JOIN g.students s JOIN LectureAchievementStudentReport la ON la.lecture = l WHERE s.username = :username AND la.present = FALSE GROUP BY l.lectureId HAVING COUNT(s.username) > 3")
    List<?> getLecturesWereStudentWasAbsent(String username);
}


