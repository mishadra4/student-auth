package com.md.sa.facade.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.dao.LectureDao;
import com.md.sa.facade.LectureAttachmentsFacade;
import com.md.sa.facade.converter.LectureAttachmentsConverter;
import com.md.sa.model.LectureDocument;
import com.md.sa.model.User;
import com.md.sa.repository.LectureDocumentRepository;

@Component
public class LectureAttachmentsFacadeImpl implements LectureAttachmentsFacade {

    private final LectureDao lectureDao;
    private final LectureDocumentRepository lectureDocumentRepository;
    private final LectureAttachmentsConverter lectureAttachmentsConverter;

    public LectureAttachmentsFacadeImpl(LectureDao lectureDao,
                                        LectureDocumentRepository lectureDocumentRepository,
                                        LectureAttachmentsConverter lectureAttachmentsConverter) {
        this.lectureDao = lectureDao;
        this.lectureDocumentRepository = lectureDocumentRepository;
        this.lectureAttachmentsConverter = lectureAttachmentsConverter;
    }

    @Override
    public void upload(Integer lectureId, MultipartFile file) {
        LectureDocument newDocument = lectureAttachmentsConverter.convertToEntity(file);
        newDocument.setLecture(lectureDao.getLecture(lectureId));
        newDocument.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        lectureDocumentRepository.save(newDocument);
    }
}
