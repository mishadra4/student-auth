package com.md.sa.facade;

import java.util.List;

import com.md.sa.facade.dto.StudentPassInOrderData;

public interface StudentPassInOrderFacade {

    List<StudentPassInOrderData> getStudentPassInOrderByLab(Integer labId);

    void orderPassIn(Integer labId);
}
