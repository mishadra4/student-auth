package com.md.sa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.md.sa.facade.StudentPassInOrderFacade;
import com.md.sa.facade.dto.StudentPassInOrderData;

@RestController
@RequestMapping("/labs/{lab_id}/pass-in-orders")
public class StudentPassInOrderController {

    private final StudentPassInOrderFacade facade;

    public StudentPassInOrderController(StudentPassInOrderFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    List<StudentPassInOrderData> findAll(@PathVariable("lab_id") Integer labId) {
        return facade.getStudentPassInOrderByLab(labId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void bookPassInOrder(@PathVariable("lab_id") Integer labId) {
        facade.orderPassIn(labId);
    }
}
