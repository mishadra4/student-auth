package com.md.sa.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.dao.LectureDao;
import com.md.sa.facade.LectureAttachmentsFacade;
import com.md.sa.facade.converter.LectureAttachmentsConverter;
import com.md.sa.facade.dto.LectureDocumentInfoData;
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
    public Pair<String, Resource> findOne(Integer lectureId, Long documentId) {
        LectureDocument document = lectureDocumentRepository.getOne(documentId);

        return Pair.of(document.getName(), new ByteArrayResource(document.getFile()));
    }

    @Override
    public List<LectureDocumentInfoData> findAll(Integer lectureId) {
        return lectureDocumentRepository.findAllByLectureId(lectureId)
                .stream()
                .map(entity -> new LectureDocumentInfoData()
                        .setId(entity.getId())
                        .setName(entity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void upload(Integer lectureId, MultipartFile file) {
        LectureDocument newDocument = lectureAttachmentsConverter.convertToEntity(file);
        newDocument.setLecture(lectureDao.getLecture(lectureId));
        newDocument.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        lectureDocumentRepository.save(newDocument);
    }
}
