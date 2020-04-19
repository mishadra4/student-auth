package com.md.sa.facade.converter;

import com.md.sa.facade.dto.GroupData;
import com.md.sa.facade.dto.LectureData;
import com.md.sa.model.Lecture;
import com.md.sa.model.Student;
import com.md.sa.service.GroupService;
import com.md.sa.service.LecturerService;
import com.md.sa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class LectureConverter implements GenericConverter<Lecture, LectureData> {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupConverter groupConverter;

    @Autowired
    private SubjectConverter subjectConverter;


    @Override
    public Lecture convertToEntity(LectureData dto) {
        Lecture lecture = new Lecture();
        lecture.setName(dto.getName());
        lecture.setLectureId(dto.getId());
        lecture.setLecturer(lecturerService.getLecturerByUsername(dto.getLecturerUsername()));
        lecture.setOrdinalNumber(dto.getOrdinalNumber());
        lecture.setDescription(dto.getDescription());

        lecture.setGroups(dto.getGroups().stream()
                .map(GroupData::getName)
                .map(groupService::getGroup)
                .collect(Collectors.toList()));

        lecture.setSubject(subjectService.getSubject(dto.getSubject().getName()));

        return lecture;
    }

    @Override
    public LectureData convertToDTO(Lecture entity) {
        LectureData dto = new LectureData();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getLectureId());
        dto.setName(entity.getName());
        dto.setOrdinalNumber(entity.getOrdinalNumber());

        dto.setGroups(entity.getGroups().stream()
                .map(groupConverter::convertToDTO)
                .collect(Collectors.toList()));

        List<String> studentIds = entity.getStudents().stream()
                .map(Student::getUsername)
                .collect(Collectors.toList());

        dto.getGroups().stream()
                .map(GroupData::getStudents)
                .flatMap(Collection::stream)
                .filter(student -> studentIds.contains(student.getUsername()))
                .forEach(studentData -> studentData.setChecked(true));

        dto.setLecturerUsername(entity.getLecturer().getUsername());

        dto.setLectureDate(entity.getLectureDate());

        dto.setSubject(subjectConverter.convertToDTO(entity.getSubject()));
        createQrCode(entity);
        dto.setFilePath(entity.getQrCodeFilepath());

        return dto;
    }

    private void createQrCode(Lecture lecture) {
        if (lecture.getQrCodeFilepath() == null || lecture.getQrCodeFilepath().isEmpty()) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                final String lectureUrl = "http://" + inetAddress.getHostAddress() + ":4200/student/lecture/" + lecture.getLectureId() + "/enroll";
                lecture.setQrCodeFilepath(lectureUrl);
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
