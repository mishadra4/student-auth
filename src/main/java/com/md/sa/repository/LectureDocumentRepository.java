package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.md.sa.model.LectureDocument;

@Repository
public interface LectureDocumentRepository extends JpaRepository<LectureDocument, Long> {

    @Query("SELECT ld FROM LectureDocument ld WHERE ld.lecture.lectureId = :lectureId")
    List<LectureDocument> findAllByLectureId(@Param("lectureId") Integer lectureId);
}
