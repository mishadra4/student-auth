package com.md.sa.rest;

import com.md.sa.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping(value = "/qrCode/lecture/{lectureId}/renew")
    public void renewQrCode(@PathVariable Integer lectureId) {
        qrCodeService.renewQrCode(lectureId);
    }

}
