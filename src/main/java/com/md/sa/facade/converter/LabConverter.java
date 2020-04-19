package com.md.sa.facade.converter;

import com.md.sa.facade.dto.GroupData;
import com.md.sa.facade.dto.LabData;
import com.md.sa.model.Groups;
import com.md.sa.model.Lab;
import com.md.sa.model.Student;
import com.md.sa.service.GroupService;
import com.md.sa.service.LecturerService;
import com.md.sa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LabConverter implements GenericConverter<Lab, LabData> {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GenericConverter<Groups, GroupData> groupConverter;
    @Autowired
    private SubjectConverter subjectConverter;


    @Override
    public Lab convertToEntity(LabData dto) {
        Lab lab = new Lab();
        lab.setName(dto.getName());
        lab.setLecturer(lecturerService.getLecturerByUsername(dto.getLecturerUsername()));
        lab.setOrdinalNumber(dto.getOrdinalNumber());
        lab.setDescription(dto.getDescription());
        lab.setGroup(groupService.getGroup(dto.getGroup().getName()));
        lab.setSubject(subjectService.getSubject(dto.getSubject().getName()));

        return lab;
    }

    @Override
    public LabData convertToDTO(Lab entity) {
        LabData dto = new LabData();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getLabId());
        dto.setName(entity.getName());
        dto.setOrdinalNumber(entity.getOrdinalNumber());
        dto.setGroup(groupConverter.convertToDTO(entity.getGroup()));
        dto.setLecturerUsername(entity.getLecturer().getUsername());
        dto.setLabDate(entity.getLabDate());
        List<String> studentIds = entity.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());

        dto.getGroup().getStudents().stream()
                .filter(student -> studentIds.contains(student.getId()))
                .forEach(studentData -> studentData.setChecked(true));

        dto.setLecturerUsername(entity.getLecturer().getUsername());

        dto.setSubject(subjectConverter.convertToDTO(entity.getSubject()));
        createQrCode(entity);
        dto.setFilePath(entity.getQrCodeFilepath());
        return dto;
    }

    private void createQrCode(Lab lab) {
        if (lab.getQrCodeFilepath() == null || lab.getQrCodeFilepath().isEmpty()) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                final String lectureUrl = "http://" + inetAddress.getHostAddress() + ":4200/student/lab/" + lab.getLabId() + "/enroll";
                lab.setQrCodeFilepath(lectureUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public LecturerService getLecturerService() {
        return lecturerService;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
