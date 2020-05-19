package com.md.sa.service.impl;

import com.md.sa.model.Lab;
import com.md.sa.model.Lecture;
import com.md.sa.model.QrCode;
import com.md.sa.service.LectureService;
import com.md.sa.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.isEmpty;

@Service
@Transactional
public class QrCodeServiceImpl implements QrCodeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LectureService lectureService;

    @Value("${qrcode.active.time.minutes}")
    private Integer activeMinutes;

    @Override
    public QrCode generateQrCode(Lecture lecture) {
        return createQrCode(lecture);
    }

    @Override
    public void renewQrCode(Integer lectureId) {
        lectureService.getLecture(lectureId).getQrCode().setEndDate(LocalDateTime.now().plusMinutes(5));
    }

    @Override
    public QrCode generateQrCode(Lab lab) {
        return createQrCode(lab);
    }

    private QrCode createQrCode(Lecture lecture) {
        if (lecture.getQrCode() == null || isEmpty(lecture.getQrCode().getQrCodePath())) {
            final String lectureUrl = "student/lecture/" + lecture.getLectureId() + "/enroll";
            final QrCode qrCode = new QrCode();
            qrCode.setQrCodePath(lectureUrl);
            qrCode.setEndDate(LocalDateTime.now().plusMinutes(activeMinutes));
            entityManager.merge(qrCode);
            lecture.setQrCode(qrCode);
            entityManager.merge(lecture);
            return qrCode;
        }
        return lecture.getQrCode();
    }

    private QrCode createQrCode(Lab lab) {
        if (lab.getQrCode() == null || isEmpty(lab.getQrCode().getQrCodePath())) {
            final String lectureUrl = "student/lab/" + lab.getLabId() + "/enroll";
            final QrCode qrCode = new QrCode();
            qrCode.setQrCodePath(lectureUrl);
            entityManager.merge(qrCode);
            lab.setQrCode(qrCode);
            entityManager.merge(lab);
            return qrCode;
        }
        return lab.getQrCode();
    }
}
