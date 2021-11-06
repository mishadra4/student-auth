package com.md.sa.facade.dto;

public class StudentPassInOrderData {

    private Integer id;

    private String username;

    private String groupName;

    private String firstName;

    private String lastName;

    public Integer getId() {
        return id;
    }

    public StudentPassInOrderData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public StudentPassInOrderData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public StudentPassInOrderData setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentPassInOrderData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentPassInOrderData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
