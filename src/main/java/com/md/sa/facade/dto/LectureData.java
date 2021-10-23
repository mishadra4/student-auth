package com.md.sa.facade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.md.sa.model.enums.LectureType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LectureData {

    private List<StudentData> students;

    private int lectureId;

    private String filePath;

    private String name;

    private LectureType type;

    private String externalLink;

    private int id;

    private String description;

    private List<GroupData> groups;

    private int ordinalNumber;

    private String lecturerUsername;

    private SubjectData subject;

    private LocalDate lectureDate;

    private boolean isOnline;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime qrCodeEndDate;


    public LocalDate getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(LocalDate lectureDate) {
        this.lectureDate = lectureDate;
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

    public List<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = groups;
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

    public LectureType getType() {
        return type;
    }

    public LectureData setType(LectureType type) {
        this.type = type;
        return this;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public LectureData setExternalLink(String externalLink) {
        this.externalLink = externalLink;
        return this;
    }

    public List<StudentData> getStudents() {
        return students;
    }

    public void setStudents(List<StudentData> students) {
        this.students = students;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getQrCodeEndDate() {
        return qrCodeEndDate;
    }

    public void setQrCodeEndDate(LocalDateTime qrCodeEndDate) {
        this.qrCodeEndDate = qrCodeEndDate;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public LectureData setOnline(boolean online) {
        isOnline = online;
        return this;
    }
}
