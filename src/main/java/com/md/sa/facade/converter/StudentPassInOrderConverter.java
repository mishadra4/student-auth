package com.md.sa.facade.converter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.md.sa.facade.dto.StudentPassInOrderData;
import com.md.sa.model.StudentPassInOrder;

@Component
public class StudentPassInOrderConverter implements GenericConverter<StudentPassInOrder, StudentPassInOrderData> {

    @Override
    public StudentPassInOrder convertToEntity(StudentPassInOrderData dto) {
        throw new RuntimeException("No operation");
    }

    @Override
    public StudentPassInOrderData convertToDTO(StudentPassInOrder entity) {
        StudentPassInOrderData dto = new StudentPassInOrderData();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getUser())
                .ifPresent(user -> {
                    dto.setUsername(user.getUsername());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());

                    Optional.ofNullable(user.getGroup())
                            .ifPresent(group -> dto.setGroupName(group.getName()));
                });

        return dto;
    }
}
