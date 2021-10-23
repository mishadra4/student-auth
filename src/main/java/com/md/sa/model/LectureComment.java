package com.md.sa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "lecture_comments")
public class LectureComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Long getId() {
        return id;
    }

    public LectureComment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LectureComment setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public LectureComment setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LectureComment setUser(User user) {
        this.user = user;
        return this;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public LectureComment setLecture(Lecture lecture) {
        this.lecture = lecture;
        return this;
    }
}
