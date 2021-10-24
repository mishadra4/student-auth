package com.md.sa.facade;

import org.springframework.web.multipart.MultipartFile;

public interface LectureAttachmentsFacade {

    void upload(Integer lectureId, MultipartFile file);

}
