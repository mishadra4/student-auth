package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.md.sa.model.LectureComment;

@Repository
public interface LectureCommentRepository extends JpaRepository<LectureComment, Long> {

    @Query("SELECT lc FROM LectureComment lc WHERE lc.lecture.lectureId = :lectureId")
    List<LectureComment> findByLectureId(@Param("lectureId") Integer lectureId);
}
