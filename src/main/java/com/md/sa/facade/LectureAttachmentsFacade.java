package com.md.sa.facade;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.md.sa.facade.dto.LectureDocumentInfoData;

public interface LectureAttachmentsFacade {

    List<LectureDocumentInfoData> findAll(Integer lectureId);

    void upload(Integer lectureId, MultipartFile file);

}
