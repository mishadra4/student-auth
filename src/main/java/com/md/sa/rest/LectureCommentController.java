package com.md.sa.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.sa.facade.LectureCommentFacade;
import com.md.sa.facade.dto.LectureCommentData;

@RestController
@RequestMapping("/lecture/{lecture_id}")
public class LectureCommentController {

    private final LectureCommentFacade lectureCommentFacade;

    public LectureCommentController(LectureCommentFacade lectureCommentFacade) {
        this.lectureCommentFacade = lectureCommentFacade;
    }

    @GetMapping("/")
    public List<LectureCommentData> findAllLectureComments(@PathVariable("lecture_id") Integer lectureId) {
        return lectureCommentFacade.findAll(lectureId);
    }

    @PostMapping("/")
    public LectureCommentData create(@PathVariable("lecture_id") Integer lectureId,
                                     @RequestBody LectureCommentData lectureComment) {
        return lectureCommentFacade.create(lectureId, lectureComment);
    }
}
