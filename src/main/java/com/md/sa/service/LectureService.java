package com.md.sa.service;

import com.md.sa.model.Lecture;
import com.md.sa.model.Student;

import java.util.List;

public interface LectureService {

    Lecture getLecture(Integer id);

    List<Lecture> getLecture(String lecturerUsername);

    Lecture saveLecture(Lecture lecture);

    void enrollStudent(final int lectureId, final Student student);

    void unEnrollStudent(int lectureId, Student student);

    List<Lecture> getLectures(String subjectId);
}
