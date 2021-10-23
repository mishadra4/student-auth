package com.md.sa.facade.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class LectureCommentData {

    private Long id;

    private String content;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime publicationDate;

    private String fullName;

    private Integer lectureId;

    public Long getId() {
        return id;
    }

    public LectureCommentData setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LectureCommentData setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public LectureCommentData setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LectureCommentData setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public LectureCommentData setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
        return this;
    }
}
