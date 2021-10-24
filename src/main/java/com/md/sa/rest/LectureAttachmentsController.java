package com.md.sa.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.facade.LectureAttachmentsFacade;

@RestController
@RequestMapping("/lecture/{lecture_id}")
public class LectureAttachmentsController {

    private final LectureAttachmentsFacade lectureAttachmentsFacade;

    public LectureAttachmentsController(LectureAttachmentsFacade lectureAttachmentsFacade) {
        this.lectureAttachmentsFacade = lectureAttachmentsFacade;
    }

    @PostMapping("/upload-attachment")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("lecture_id") Integer lectureId,
                       @RequestParam("file") MultipartFile file) {
        lectureAttachmentsFacade.upload(lectureId, file);
    }
}
