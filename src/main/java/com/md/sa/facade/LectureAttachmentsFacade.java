package com.md.sa.facade;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.facade.dto.LectureDocumentInfoData;

public interface LectureAttachmentsFacade {

    Pair<String, Resource> findOne(Integer lectureId, Long documentId);

    List<LectureDocumentInfoData> findAll(Integer lectureId);

    void upload(Integer lectureId, MultipartFile file);

}
