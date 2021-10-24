package com.md.sa.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.facade.LectureAttachmentsFacade;

@RestController
@RequestMapping("/lecture/{lecture_id}")
public class LectureAttachmentsController {

    private final LectureAttachmentsFacade lectureAttachmentsFacade;

    public LectureAttachmentsController(LectureAttachmentsFacade lectureAttachmentsFacade) {
        this.lectureAttachmentsFacade = lectureAttachmentsFacade;
    }

    @PostMapping("/attachments")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@PathVariable("lecture_id") Integer lectureId,
                       @RequestParam("file") MultipartFile file) {
        lectureAttachmentsFacade.upload(lectureId, file);
    }
}
