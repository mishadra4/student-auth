package com.md.sa.facade.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.md.sa.dao.LectureDao;
import com.md.sa.facade.LectureCommentFacade;
import com.md.sa.facade.converter.LectureCommentConverter;
import com.md.sa.facade.dto.LectureCommentData;
import com.md.sa.model.LectureComment;
import com.md.sa.model.User;
import com.md.sa.repository.LectureCommentRepository;


@Component
public class LectureCommentFacadeImpl implements LectureCommentFacade {

    private final LectureCommentRepository lectureCommentRepository;
    private final LectureDao lectureDao;
    private final LectureCommentConverter lectureCommentConverter;

    public LectureCommentFacadeImpl(LectureCommentRepository lectureCommentRepository,
                                    LectureCommentConverter lectureCommentConverter,
                                    LectureDao lectureDao) {
        this.lectureCommentRepository = lectureCommentRepository;
        this.lectureCommentConverter = lectureCommentConverter;
        this.lectureDao = lectureDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LectureCommentData> findAll(Integer lectureId) {
        return lectureCommentRepository.findByLectureId(lectureId)
                .stream()
                .map(lectureCommentConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LectureCommentData create(Integer lectureId, LectureCommentData comment) {
        LectureComment newComment = lectureCommentConverter.convertToEntity(comment);
        newComment.setPublicationDate(LocalDateTime.now());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newComment.setUser(user);
        newComment.setLecture(lectureDao.getLecture(lectureId));

        return lectureCommentConverter.convertToDTO(lectureCommentRepository.save(newComment));
    }
}
