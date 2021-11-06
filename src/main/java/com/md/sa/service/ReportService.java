package com.md.sa.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

public interface ReportService {

    Workbook generateReport(String subjectName, HttpServletResponse response) throws IOException;

    String generateLabReport(String subjectName, String realPath) throws IOException;
}
