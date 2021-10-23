package com.md.sa.facade.dto;

import java.util.List;

public class SubjectData {
    public List<GroupData> groups;
    private int id;
    private String name;
    private String lecturerUsername;
    private List<String> groupNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturerUsername() {
        return lecturerUsername;
    }

    public void setLecturerUsername(String lecturerUsername) {
        this.lecturerUsername = lecturerUsername;
    }

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = groups;
    }
}
