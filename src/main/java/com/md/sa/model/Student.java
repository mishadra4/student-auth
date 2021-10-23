package com.md.sa.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Objects;

@Entity(name = "student")
public class Student extends User {

    @Column(name = "student_id")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_group_id")
    private Groups groups;

    @ManyToMany(mappedBy = "students")
    private List<Lecture> lectures;

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getUsername(), student.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groups, lectures);
    }
}
