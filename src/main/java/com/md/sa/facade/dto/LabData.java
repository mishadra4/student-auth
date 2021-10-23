package com.md.sa.facade.dto;

import java.time.LocalDate;
import java.util.List;

public class LabData {

    private List<StudentData> students;

    private int labsId;

    private String filePath;

    private String name;

    private int id;

    private String description;

    private GroupData group;

    private int ordinalNumber;

    private String lecturerUsername;

    private SubjectData subject;

    private LocalDate labDate;

    public LocalDate getLabDate() {
        return labDate;
    }

    public void setLabDate(LocalDate labDate) {
        this.labDate = labDate;
    }

    public SubjectData getSubject() {
        return subject;
    }

    public void setSubject(SubjectData subject) {
        this.subject = subject;
    }

    public String getLecturerUsername() {
        return lecturerUsername;
    }

    public void setLecturerUsername(String lecturerUsername) {
        this.lecturerUsername = lecturerUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentData> getStudents() {
        return students;
    }

    public void setStudents(List<StudentData> students) {
        this.students = students;
    }

    public GroupData getGroup() {
        return group;
    }

    public void setGroup(GroupData group) {
        this.group = group;
    }

    public int getLabsId() {
        return labsId;
    }

    public void setLabsId(int labsId) {
        this.labsId = labsId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
