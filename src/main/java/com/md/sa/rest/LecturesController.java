package com.md.sa.rest;

import com.md.sa.facade.dto.LectureDTO;
import com.md.sa.facade.LectureFacade;
import com.md.sa.facade.dto.LectureData;
import com.md.sa.model.Groups;
import com.md.sa.model.Lecture;
import com.md.sa.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@RestController
@CrossOrigin
public class LecturesController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LectureFacade lectureFacade;

    @RequestMapping(value = "lecturer/{username}/lectures", method = RequestMethod.GET)
    public List<LectureDTO> lectures(@PathVariable final String username) {
        return lecturerService.getLecturerByUsername(username).getLectures()
                .stream()
                .filter(Objects::nonNull)
                .map(this::populateLectures)
                .collect(toList());
    }

    @RequestMapping("/v1/lecture/{id}")
    public LectureData getLecture(@PathVariable final Integer id) {
        return lectureFacade.getLecture(id);
    }

    @RequestMapping(value = "/subject/{subjectId}/lectures", method = RequestMethod.GET)
    public List<LectureData> getLectures(@PathVariable final String subjectId) {
        return lectureFacade.getLectures(subjectId);
    }

    @RequestMapping(value = "lecture/create", method = RequestMethod.POST)
    public ResponseEntity createLecture(@RequestBody final LectureData lectureData)
    {
        lectureFacade.createLecture(lectureData);
        return ResponseEntity.ok().build();
    }

    private LectureDTO populateLectures(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(lecture.getLectureId());
        lectureDTO.setExternalLink(lecture.getExternalLink());
        lectureDTO.setType(lecture.getType());
        lectureDTO.setLecturerUsername(lecture.getLecturer().getUsername());
        lectureDTO.setOrdinalNumber(lecture.getOrdinalNumber());
        lectureDTO.setName(lecture.getName());
        lectureDTO.setDescription(lecture.getDescription());
        lectureDTO.setGroups(lecture.getGroups().stream().map(Groups::getName).collect(toList()));
        return lectureDTO;
    }
}
