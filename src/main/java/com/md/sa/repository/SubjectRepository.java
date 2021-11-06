package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.md.sa.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject AS s JOIN s.lectures l JOIN l.groups g JOIN g.students st JOIN LectureAchievementStudentReport la ON la.lecture = l WHERE st.username = :username AND la.present = FALSE GROUP BY s.id HAVING COUNT(st.username) >= 3")
    List<Subject> getSubjectsWereStudentWasAbsent(String username);

}
