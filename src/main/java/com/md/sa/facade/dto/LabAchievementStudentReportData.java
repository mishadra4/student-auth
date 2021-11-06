package com.md.sa.facade.dto;

public class LabAchievementStudentReportData {

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

    public LabAchievementStudentReportData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LabAchievementStudentReportData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public LabAchievementStudentReportData setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LabAchievementStudentReportData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LabAchievementStudentReportData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getGrade() {
        return grade;
    }

    public LabAchievementStudentReportData setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public Boolean getPresent() {
        return present;
    }

    public LabAchievementStudentReportData setPresent(Boolean present) {
        this.present = present;
        return this;
    }
}
