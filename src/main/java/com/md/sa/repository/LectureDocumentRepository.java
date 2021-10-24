package com.md.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.md.sa.model.LectureDocument;

@Repository
public interface LectureDocumentRepository extends JpaRepository<LectureDocument, Long> {
}
