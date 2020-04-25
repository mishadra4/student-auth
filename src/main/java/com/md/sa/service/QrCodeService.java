package com.md.sa.service;

import com.md.sa.model.Lab;
import com.md.sa.model.Lecture;
import com.md.sa.model.QrCode;

public interface QrCodeService {

    QrCode generateQrCode(Lecture lecture);

    QrCode generateQrCode(Lab lab);

    void renewQrCode(Integer lectureId);
}
