package com.md.sa.job;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.md.sa.model.Subject;
import com.md.sa.model.User;
import com.md.sa.repository.StudentRepository;
import com.md.sa.repository.SubjectRepository;
import com.md.sa.service.EmailService;

import static java.lang.String.format;

@Component
public class StudentGapNotifier {

    private static final Integer ONE_WEEK = 1000 * 60 * 60 * 24 * 7;

    private static final String MESSAGE = "Ти був відсутній на таких лекціях більше трьох разів: %s , звʼяжись з викладачем, щоб тебе не відрахували";
    private static final String SUBJECT = "Нагадування про погане відвідування";
    @Autowired
    private EmailService emailService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Scheduled(fixedRate = ONE_WEEK)
    public void notifyStudents() {
        studentRepository.findAll().stream()
            .map(User::getUsername)
            .forEach(this::sendNotification);
    }

    private void sendNotification(String username) {
        final List<Subject> subjectList = subjectRepository.getSubjectsWereStudentWasAbsent(username);
        String subjectNames = subjectList.stream().map(Subject::getName).collect(Collectors.joining(", "));
        emailService.sendSimpleMessage(username, SUBJECT, format(MESSAGE, subjectNames));
    }
}
