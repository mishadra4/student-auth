package com.md.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_group_mapping",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Groups> groups;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<Lecture> lectures;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<Lab> labs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public Set<Lab> getLabs() {
        return labs;
    }

    public void setLabs(Set<Lab> labs) {
        this.labs = labs;
    }
}
