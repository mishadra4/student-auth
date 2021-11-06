package com.md.sa.facade.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.md.sa.facade.StudentPassInOrderFacade;
import com.md.sa.facade.converter.StudentPassInOrderConverter;
import com.md.sa.facade.dto.StudentPassInOrderData;
import com.md.sa.model.Lab;
import com.md.sa.model.StudentPassInOrder;
import com.md.sa.model.User;
import com.md.sa.repository.LabRepository;
import com.md.sa.repository.StudentPassInOrderRepository;

@Component
public class StudentPassInOrderFacadeImpl implements StudentPassInOrderFacade {

    private final LabRepository labRepository;
    private final StudentPassInOrderRepository repository;
    private final StudentPassInOrderConverter converter;

    public StudentPassInOrderFacadeImpl(LabRepository labRepository,
                                        StudentPassInOrderRepository repository,
                                        StudentPassInOrderConverter converter) {
        this.labRepository = labRepository;
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentPassInOrderData> getStudentPassInOrderByLab(Integer labId) {
        return repository.findAllByLabId(labId)
                .stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void orderPassIn(Integer labId) {
        Lab lab = labRepository.findById(labId).orElseThrow(() -> new RuntimeException("no found lab by id " + labId));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.nonNull(lab) && Objects.nonNull(user)) {
            StudentPassInOrder existencePassInOrder = repository.findOneByLabAndUser(lab.getLabId(), user.getUsername());
            if (Objects.nonNull(existencePassInOrder)) {
                repository.delete(existencePassInOrder);
            }
            StudentPassInOrder newPassInOrder = new StudentPassInOrder()
                    .setLab(lab)
                    .setUser(user);
            repository.save(newPassInOrder);
        }
    }
}
