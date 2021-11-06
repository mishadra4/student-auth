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
@Table(name = "lecture_achievement_student_reports")
public class LectureAchievementStudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "present")
    private Boolean present;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Integer getId() {
        return id;
    }

    public LectureAchievementStudentReport setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGrade() {
        return grade;
    }

    public LectureAchievementStudentReport setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public Boolean getPresent() {
        return present;
    }

    public LectureAchievementStudentReport setPresent(Boolean present) {
        this.present = present;
        return this;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public LectureAchievementStudentReport setLecture(Lecture lecture) {
        this.lecture = lecture;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LectureAchievementStudentReport setUser(User user) {
        this.user = user;
        return this;
    }
}
