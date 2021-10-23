package com.md.sa.facade;

import com.md.sa.facade.dto.LectureData;

import java.util.List;

public interface LectureFacade {

    LectureData getLecture(final int id);

    List<LectureData> getLectures(String subjectId);

    void createLecture(LectureData lectureData);
}
