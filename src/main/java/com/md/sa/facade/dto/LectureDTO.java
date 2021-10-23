package com.md.sa.facade.dto;

import com.md.sa.model.Subject;

import java.util.List;

public class LectureDTO {

    private String name;

    private Integer id;

    private String lecturerUsername;

    private String description;

    private List<String> groups;

    private Integer ordinalNumber;

    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLecturerUsername(String lecturerUsername) {
        this.lecturerUsername = lecturerUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getLecturerUsername() {
        return lecturerUsername;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }
}
