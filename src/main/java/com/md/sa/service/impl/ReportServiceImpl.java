package com.md.sa.service.impl;

import com.md.sa.model.Groups;
import com.md.sa.model.Lab;
import com.md.sa.model.Lecture;
import com.md.sa.model.Student;
import com.md.sa.model.Subject;
import com.md.sa.service.ReportService;
import com.md.sa.service.SubjectService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private static final String PRESENT = " ";
    private static final String NON_PRESENT = "H";
    @Autowired
    private SubjectService subjectService;

    @Override
    public String generateLabReport(String subjectName, String realPath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Список відвідування");

            Subject subject = subjectService.getSubject(subjectName);

//            Set<Lab> labs = subject.getLabs();
//            List<Student> students = subject.getGroups()
//                    .stream()
//                    .map(Groups::getStudents)
//                    .flatMap(Collection::stream)
//                    .collect(Collectors.toList());

//            createLabRow(labs, sheet);
            int rowCount = 0;
            for (Groups group : subject.getGroups().stream().filter(groups -> groups.getName() != null)
                    .sorted(Comparator.comparing(Groups::getName)).collect(Collectors.toList())) {

                Set<Lab> labs = subject.getLabs().stream()
                        .filter(lab -> lab.getGroup().equals(group))
                        .collect(Collectors.toSet());

                createLabRow(labs, sheet, ++rowCount, group.getName());
                for (Student student : group.getStudents().stream()
                        .sorted(Comparator.comparing(s -> (s.getLastName() + s.getFirstName())))
                        .collect(Collectors.toList())) {
                    int columnCount = 0;
                    Row row = sheet.createRow(++rowCount);
                    Cell studentCell = row.createCell(columnCount);
                    studentCell.setCellValue(student.getLastName() + " " + student.getFirstName());
                    CellStyle cs = workbook.createCellStyle();
                    cs.setWrapText(true);
                    studentCell.setCellStyle(cs);
                    for (Lab lab : labs) {
                        Cell cell = row.createCell(++columnCount);
                        final boolean isPresent = lab.getStudents().contains(student) || !lab.getGroup().equals(student.getGroups());
                        cell.setCellValue(isPresent ? PRESENT : NON_PRESENT);
                    }
                }
                rowCount++;
            }

            FileOutputStream fileOut = new FileOutputStream(realPath);
            workbook.write(fileOut);
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOut)) {
                bos.flush();
            }
            fileOut.close();
        }
        return realPath;
    }

    private void createGroupRow(String name, Sheet sheet, int rowCount) {
        final Row row = sheet.createRow(rowCount);
        final Cell cell = row.createCell(0);
        cell.setCellValue(name);
    }

    @Override
    public String generateReport(final String subjectName, final String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Список відвідування");

            Subject subject = subjectService.getSubject(subjectName);

            Set<Lecture> lectures = subject.getLectures();

            createLectureRow(lectures, sheet);
            int rowCount = 0;

            for (Groups group : subject.getGroups().stream().filter(groups -> groups.getName() != null)
                    .sorted(Comparator.comparing(Groups::getName)).collect(Collectors.toList())) {
                createGroupRow(group.getName(), sheet, ++rowCount);
                for (Student student : group.getStudents().stream()
                        .sorted(Comparator.comparing(s -> (s.getLastName() + s.getFirstName())))
                        .collect(Collectors.toList())) {
                    Row row = sheet.createRow(++rowCount);
                    int columnCount = 0;
                    Cell studentCell = row.createCell(columnCount);
                    studentCell.setCellValue(student.getFirstName() + " " + student.getLastName());
                    CellStyle cs = workbook.createCellStyle();
                    cs.setWrapText(true);
                    studentCell.setCellStyle(cs);
                    for (Lecture lecture : lectures) {
                        Cell cell = row.createCell(++columnCount);
                        final boolean isPresent = lecture.getStudents().contains(student);
                        cell.setCellValue(isPresent ? PRESENT : NON_PRESENT);
                    }
                }
                rowCount++;
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOut)) {
                bos.flush();
            }
            fileOut.close();
        }
        return filePath;
    }

    private void createLectureRow(Set<Lecture> lectures, Sheet sheet) {
        Row row = sheet.createRow(0);
        int columnCount = 1;
        for (Lecture lecture : lectures) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(lecture.getName());
        }
    }

    private void createLabRow(Set<Lab> labs, Sheet sheet, int i, String name) {
        Row row = sheet.createRow(i);
        int columnCount = 1;
        Cell cell = row.createCell(0);
        cell.setCellValue(name);
        for (Lab lab : labs) {
            cell = row.createCell(columnCount++);
            cell.setCellValue(lab.getName());
        }
    }
}
