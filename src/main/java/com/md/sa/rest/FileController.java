package com.md.sa.rest;

import com.md.sa.csv.CSVLoader;
import com.md.sa.dao.AuthorityDao;
import com.md.sa.facade.dto.SubjectData;
import com.md.sa.model.Authority;
import com.md.sa.model.Lecturer;
import com.md.sa.model.Student;
import com.md.sa.model.Subject;
import com.md.sa.model.User;
import com.md.sa.service.GroupService;
import com.md.sa.service.LecturerService;
import com.md.sa.service.StudentService;
import com.md.sa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;


@Controller
public class FileController {

    private static final String STUDENT = "STUDENT";
    private static final String LECTURER = "LECTURER";

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private CSVLoader csvLoader;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/uploadStudents", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {

            try {
                final List<StudentData> studentDataList = csvLoader.getStudents(convert(file));
                saveStudents(studentDataList);

                return "Students were saved successfully";

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    private void saveStudents(List<StudentData> studentDataList) {
        studentDataList.forEach(this::saveStudent);
    }

    private void saveStudent(StudentData studentData) {
        Student student = studentService.getStudent(studentData.getUsername());
        if (student == null) {
            student = new Student();
            student.setUsername(studentData.getUsername());
            student.setLastName(studentData.getLastName());
            student.setFirstName(studentData.getFirstName());
            student.setId(studentData.getId());
            student.setPassword(passwordEncoder.encode(studentData.getPassword()));
            student.setGroups(groupService.getGroup(studentData.getGroupName()));
            student.setEnabled(TRUE);
            createAuthority(student, STUDENT);
            studentService.save(student);
        }
    }

    private void createAuthority(final User user, String authorityName) {
        Authority authority = authorityDao.getAuthority(authorityName);
        user.setAuthorities(Collections.singletonList(authority));
    }

    @RequestMapping(value = "/uploadLecturers", method = RequestMethod.POST)
    public @ResponseBody
    String handleLecturerUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                List<Lecturer> lecturers = csvLoader.getLecturers(convert(file));
                lecturerService.saveAll(lecturers);
                lecturers.forEach(lecturer -> createAuthority(lecturer, LECTURER));

                return "Lecturers were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    @RequestMapping(value = "/uploadGroups", method = RequestMethod.POST)
    public @ResponseBody
    String handleGroupUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                groupService.saveAll(csvLoader.getGroups(convert(file)));

                return "Groups were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }


    @RequestMapping(value = "/uploadSubjects", method = RequestMethod.POST)
    public @ResponseBody
    String handleSubjectUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                List<SubjectData> subjectDataList = csvLoader.getSubjects(convert(file));
                saveSubjects(subjectDataList);
                return "Subjects were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    private void saveSubjects(final List<SubjectData> subjectDataList) {
        subjectDataList.forEach(this::saveSubject);
    }

    private void saveSubject(final SubjectData subjectData) {
        Subject subject = subjectService.getSubject(subjectData.getName());
        if (subject == null) {
            subject = new Subject();
            subject.setName(subjectData.getName());
            subject.setLecturer(lecturerService.getLecturerByUsername(subjectData.getLecturerUsername()));
        }
        if (subject.getGroups() != null) {
            subject.getGroups().add(groupService.getGroup(subjectData.getGroupNames().get(0)));
        } else {
            subject.setGroups(subjectData.getGroupNames().stream().map(groupService::getGroup).collect(Collectors.toSet()));
        }
        subjectService.save(subject);
    }

    public CSVLoader getCsvLoader() {
        return csvLoader;
    }

    public void setCsvLoader(CSVLoader csvLoader) {
        this.csvLoader = csvLoader;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
