package com.md.sa.qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "C:/qrcodes/";
    private static final int width = 800;
    private static final int height = 800;


    public static String generateQRCodeImage(int lectureId) throws WriterException, IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        final String lectureUrl = "http://" + inetAddress.getHostAddress() + ":4200/lecture/" + lectureId + "/enroll";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(lectureUrl, BarcodeFormat.QR_CODE, width, height);

        String filePath = QR_CODE_IMAGE_PATH + lectureId + ".png";


        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return filePath;
    }
}
