package com.md.sa.dao;

import com.md.sa.model.Lecture;

import java.util.List;


public interface LectureDao {

    Lecture getLecture(Integer id);

    List<Lecture> getLectures(String lecturerUsername);

    Lecture saveLecture(Lecture lecture);

    List<Lecture> getLecturesBySubject(String subjectId);
}
