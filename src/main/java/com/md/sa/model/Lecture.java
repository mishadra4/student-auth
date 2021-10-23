package com.md.sa.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "lecture")
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Integer lectureId;

    @Column(name = "name")
    private String name;

    @Column(name = "ordinal_number")
    private Integer ordinalNumber;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lecture_group_mapping",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Groups> groups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lecture_student_mapping",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qrcode_id", referencedColumnName = "id")
    private QrCode qrCode;

    private LocalDate lectureDate;

    public LocalDate getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(LocalDate lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public QrCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QrCode qrCode) {
        this.qrCode = qrCode;
    }
}
