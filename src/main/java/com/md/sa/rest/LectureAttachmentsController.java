package com.md.sa.rest;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable("lecture_id") Integer lectureId,
                                   @PathVariable("id") Long id) {
        Pair<String, Resource> attachmentPair = lectureAttachmentsFacade.findOne(lectureId, id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", attachmentPair.getFirst()))
                .body(attachmentPair.getSecond());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@PathVariable("lecture_id") Integer lectureId,
                       @RequestParam("file") MultipartFile file) {
        lectureAttachmentsFacade.upload(lectureId, file);
    }
}
