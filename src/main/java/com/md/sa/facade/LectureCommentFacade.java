package com.md.sa.facade;

import java.util.List;

import com.md.sa.facade.dto.LectureCommentData;

public interface LectureCommentFacade {

    List<LectureCommentData> findAll(Integer lectureId);

    LectureCommentData create(Integer lectureId, LectureCommentData comment);

}
