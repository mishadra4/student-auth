package com.md.sa.service;

import java.io.IOException;

public interface ReportService {

    String generateReport(String subjectName, String filePath) throws IOException;

    String generateLabReport(String subjectName, String realPath) throws IOException;
}
