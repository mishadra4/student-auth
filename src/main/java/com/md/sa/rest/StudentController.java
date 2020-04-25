package com.md.sa.rest;

import com.md.sa.model.Student;
import com.md.sa.service.LabService;
import com.md.sa.service.LectureService;
import com.md.sa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private LabService labService;

    @ResponseBody
    @RequestMapping(value = "/lecture/{lectureId}/student/{username}/enroll", method = RequestMethod.POST)
    public ResponseEntity enrollStudent(@PathVariable final int lectureId, @PathVariable final String username) {
        final Student student = studentService.getStudent(username);
        lectureService.enrollStudent(lectureId, student, true);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/lecture/{lectureId}/student/{username}/check", method = RequestMethod.POST)
    public ResponseEntity checkStudent(@PathVariable final int lectureId, @PathVariable final String username) {
        final Student student = studentService.getStudent(username);
        boolean isPresent = lectureService.getLecture(lectureId).getStudents().contains(student);
        if (isPresent) {
            lectureService.unEnrollStudent(lectureId, student);
        } else {
            lectureService.enrollStudent(lectureId, student, false);
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/lab/{labId}/student/{username}/check", method = RequestMethod.POST)
    public ResponseEntity checkStudentForLab(@PathVariable final int labId, @PathVariable final String username) {
        final Student student = studentService.getStudent(username);
        boolean isPresent = labService.getLab(labId).getStudents().contains(student);
        if (isPresent) {
            labService.unEnrollStudent(labId, student);
        } else {
            labService.enrollStudent(labId, student, false);
        }
        return ResponseEntity.ok().build();
    }
}
