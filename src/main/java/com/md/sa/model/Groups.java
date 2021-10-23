package com.md.sa.model;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Groups")
@Table(name = "group_")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name= "course")
    private Integer courseNumber;

    @OneToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Subject> subjects;

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

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return name;
    }
}
