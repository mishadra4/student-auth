package com.md.sa.facade.dto;

public class LectureAchievementStudentReportData {

    private Integer id;

    private String fullName;

    private Integer grade;

    private Boolean present;

    public Integer getId() {
        return id;
    }

    public LectureAchievementStudentReportData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LectureAchievementStudentReportData setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getGrade() {
        return grade;
    }

    public LectureAchievementStudentReportData setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public Boolean getPresent() {
        return present;
    }

    public LectureAchievementStudentReportData setPresent(Boolean present) {
        this.present = present;
        return this;
    }
}
