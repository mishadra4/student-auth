package com.md.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lecture_documents")
public class LectureDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Long getId() {
        return id;
    }

    public LectureDocument setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LectureDocument setName(String name) {
        this.name = name;
        return this;
    }

    public byte[] getFile() {
        return file;
    }

    public LectureDocument setFile(byte[] file) {
        this.file = file;
        return this;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public LectureDocument setLecture(Lecture lecture) {
        this.lecture = lecture;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LectureDocument setUser(User user) {
        this.user = user;
        return this;
    }
}
