package com.md.sa.facade.converter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.md.sa.model.LectureDocument;

@Component
public class LectureAttachmentsConverter implements GenericConverter<LectureDocument, MultipartFile> {

    @Override
    public LectureDocument convertToEntity(MultipartFile dto) {
        LectureDocument lectureDocument = new LectureDocument();
        try {
            lectureDocument.setFile(dto.getBytes());
            lectureDocument.setName(dto.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lectureDocument;
    }

    @Override
    public MultipartFile convertToDTO(LectureDocument entity) {
        throw new UnsupportedOperationException();
    }
}
