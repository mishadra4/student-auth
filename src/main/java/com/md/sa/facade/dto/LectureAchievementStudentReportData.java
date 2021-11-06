package com.md.sa.facade.dto;

public class LectureAchievementStudentReportData {

    private Integer id;

    private String username;

    private String groupName;

    private String firstName;

    private String lastName;

    private Integer grade;

    private Boolean present;

    public Integer getId() {
        return id;
    }

    public LectureAchievementStudentReportData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LectureAchievementStudentReportData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public LectureAchievementStudentReportData setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LectureAchievementStudentReportData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LectureAchievementStudentReportData setLastName(String lastName) {
        this.lastName = lastName;
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
