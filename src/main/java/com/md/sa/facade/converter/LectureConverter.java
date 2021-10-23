package com.md.sa.facade.converter;

import com.md.sa.facade.dto.GroupData;
import com.md.sa.facade.dto.LectureData;
import com.md.sa.model.Lecture;
import com.md.sa.model.QrCode;
import com.md.sa.model.Student;
import com.md.sa.service.GroupService;
import com.md.sa.service.LecturerService;
import com.md.sa.service.QrCodeService;
import com.md.sa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.EMPTY;

import static com.md.sa.model.enums.LectureType.OFFLINE;
import static com.md.sa.model.enums.LectureType.ONLINE;


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

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    public Lecture convertToEntity(LectureData dto) {
        Lecture lecture = new Lecture();
        lecture.setName(dto.getName());
        lecture.setExternalLink(dto.getExternalLink());
        lecture.setType(dto.isOnline() ? ONLINE : OFFLINE);
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
        dto.setExternalLink(entity.getExternalLink());
        dto.setType(entity.getType());
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

        final QrCode qrCode = qrCodeService.generateQrCode(entity);
        dto.setFilePath(Optional.ofNullable(qrCode)
                .map(QrCode::getQrCodePath)
                .orElse(EMPTY));

        dto.setQrCodeEndDate(Optional.ofNullable(qrCode)
        .map(QrCode::getEndDate)
        .orElse(LocalDateTime.now()));

        return dto;
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
