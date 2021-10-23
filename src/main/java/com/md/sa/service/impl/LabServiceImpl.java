package com.md.sa.service.impl;

import com.md.sa.dao.LabDao;
import com.md.sa.model.Lab;
import com.md.sa.model.Student;
import com.md.sa.repository.LabRepository;
import com.md.sa.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private LabDao labDao;

    @Override
    public List<Lab> getLabs(String subjectId) {
        return labDao.getLabs(subjectId);
    }

    @Override
    public Lab getLab(final Integer id) {
        return labRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveLab(Lab lab) {
        lab.setLabDate(LocalDate.now().plusDays(1));
        labRepository.save(lab);
    }

    @Override
    public void unEnrollStudent(int labId, Student student) {
        final Lab lab = getLab(labId);
        lab.getStudents().remove(student);
        labRepository.save(lab);
    }

    @Override
    public void enrollStudent(int labId, Student student, boolean checkQr) {
        final Lab lab = getLab(labId);
        if (!checkQr || lab.getQrCode().getEndDate().isBefore(LocalDateTime.now())) {
            lab.getStudents().add(student);
            labRepository.save(lab);
        }
    }
}
