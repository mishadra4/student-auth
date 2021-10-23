package com.md.sa.facade.converter;

import org.springframework.stereotype.Component;

import com.md.sa.facade.dto.LectureCommentData;
import com.md.sa.model.LectureComment;

@Component
public class LectureCommentConverter implements GenericConverter<LectureComment, LectureCommentData> {

    @Override
    public LectureComment convertToEntity(LectureCommentData dto) {
        return new LectureComment()
                .setContent(dto.getContent());
    }

    @Override
    public LectureCommentData convertToDTO(LectureComment entity) {
        return new LectureCommentData()
                .setContent(entity.getContent())
                .setPublicationDate(entity.getPublicationDate())
                .setFullName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());
    }
}
