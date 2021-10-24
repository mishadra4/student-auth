package com.md.sa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.facade.LectureAttachmentsFacade;
import com.md.sa.facade.dto.LectureDocumentInfoData;

@RestController
@RequestMapping("/lecture/{lecture_id}/attachments")
public class LectureAttachmentsController {

    private final LectureAttachmentsFacade lectureAttachmentsFacade;

    public LectureAttachmentsController(LectureAttachmentsFacade lectureAttachmentsFacade) {
        this.lectureAttachmentsFacade = lectureAttachmentsFacade;
    }

    @GetMapping
    public List<LectureDocumentInfoData> findAll(@PathVariable("lecture_id") Integer lectureId) {
        return lectureAttachmentsFacade.findAll(lectureId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@PathVariable("lecture_id") Integer lectureId,
                       @RequestParam("file") MultipartFile file) {
        lectureAttachmentsFacade.upload(lectureId, file);
    }
}
