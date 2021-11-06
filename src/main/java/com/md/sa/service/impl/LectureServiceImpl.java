package com.md.sa.service.impl;

import com.md.sa.dao.LectureDao;
import com.md.sa.model.Groups;
import com.md.sa.model.Lecture;
import com.md.sa.model.LectureAchievementStudentReport;
import com.md.sa.model.Student;
import com.md.sa.repository.LectureAchievementStudentReportRepository;
import com.md.sa.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureDao lectureDao;
    @Autowired
    private LectureAchievementStudentReportRepository lectureAchievementStudentReportRepository;

    @Override
    public Lecture getLecture(Integer id) {
        return lectureDao.getLecture(id);
    }

    @Override
    public List<Lecture> getLecture(String lecturerUsername) {
        return lectureDao.getLectures(lecturerUsername);
    }

    @Override
    public void enrollStudent(int lectureId, Student student, boolean checkQr) {
        final Lecture lecture = getLecture(lectureId);
        if (!checkQr || lecture.getQrCode().getEndDate().isAfter(LocalDateTime.now())) {
            lecture.getStudents().add(student);
            saveLecture(lecture);
        } else
        {
            throw new IllegalArgumentException("Qr code is expired");
        }
    }

    @Override
    public List<Lecture> getLectures(String subjectId) {
        return lectureDao.getLecturesBySubject(subjectId);
    }

    @Override
    public void unEnrollStudent(int lectureId, Student student) {
        final Lecture lecture = getLecture(lectureId);
        lecture.getStudents().remove(student);
        saveLecture(lecture);
    }

    @Override
    @Transactional
    public Lecture saveLecture(Lecture lecture) {
        lecture.setLectureDate(LocalDate.now().plusDays(1));
        Lecture result = lectureDao.saveLecture(lecture);
        lecture.getGroups().stream()
            .map(Groups::getStudents)
            .flatMap(Collection::stream)
            .forEach(student -> createAchievement(student, result));
        return result;
    }

    private void createAchievement(Student student, Lecture lecture) {
        LectureAchievementStudentReport achievement = new LectureAchievementStudentReport();
        achievement.setLecture(lecture);
        achievement.setUser(student);
        achievement.setPresent(false);
        lectureAchievementStudentReportRepository.save(achievement);
    }

    public LectureDao getLectureDao() {
        return lectureDao;
    }

    public void setLectureDao(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }
}
