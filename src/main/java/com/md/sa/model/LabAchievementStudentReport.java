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
@Table(name = "lab_achievement_student_reports")
public class LabAchievementStudentReport {

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
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Integer getId() {
        return id;
    }

    public LabAchievementStudentReport setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGrade() {
        return grade;
    }

    public LabAchievementStudentReport setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public Boolean getPresent() {
        return present;
    }

    public LabAchievementStudentReport setPresent(Boolean present) {
        this.present = present;
        return this;
    }

    public Lab getLab() {
        return lab;
    }

    public LabAchievementStudentReport setLab(Lab lab) {
        this.lab = lab;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LabAchievementStudentReport setUser(User user) {
        this.user = user;
        return this;
    }
}
