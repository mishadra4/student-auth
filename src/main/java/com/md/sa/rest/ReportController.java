package com.md.sa.rest;

import com.md.sa.service.ReportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@CrossOrigin
@RestController
public class ReportController {
    private static final String FILE_NAME = "Report.xlsx";
    private static final String FILE_PATH = "/reports/";

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/app/report/lectures/subject/{subjectName}")
    public void getLectureReport(@PathVariable String subjectName, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        File dir = new File(FILE_PATH);
//        File file = new File(FILE_PATH + subjectName + "_" + FILE_NAME);

//        String realPath = file.getCanonicalPath();

        Workbook workbook = reportService.generateReport(subjectName, response);

//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType(APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=\"%s\"", subjectName + "_" + FILE_NAME));
        response.flushBuffer();
    }

    @RequestMapping(value = "/app/report/labs/subject/{subjectName}")
    public void getLabReport(@PathVariable String subjectName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        File dir = new File(FILE_PATH);
        File file = new File(FILE_PATH + subjectName + "_" + FILE_NAME);

        String realPath = file.getCanonicalPath();

        if(!file.exists())
        {
            dir.mkdir();
            file.createNewFile();
        }

        reportService.generateLabReport(subjectName, realPath);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        try (InputStream is = new FileInputStream(realPath)) {
            IOUtils.copy(is, response.getOutputStream());
        }
        response.flushBuffer();
    }

}
