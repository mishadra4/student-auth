package com.md.sa.facade.converter;

import com.md.sa.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter implements GenericConverter<Student, StudentData> {

    @Override
    public Student convertToEntity(StudentData dto) {
        return null;
    }

    @Override
    public StudentData convertToDTO(Student entity) {
        StudentData dto = new StudentData();
        dto.setId(entity.getId());
        dto.setGroupName(entity.getGroups().getName());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        return dto;
    }
}
