package com.md.sa.rest;

import com.md.sa.facade.LabFacade;
import com.md.sa.facade.dto.LabData;
import com.md.sa.facade.dto.LectureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LabsController {

    @Autowired
    private LabFacade labFacade;

    @RequestMapping(value = "/subject/{subjectId}/labs", method = RequestMethod.GET)
    public List<LabData> getLabs(@PathVariable final String subjectId) {
        return labFacade.getLabs(subjectId);
    }

    @RequestMapping(value = "/v1/lab/{labId}")
    public LabData getLab(@PathVariable final Integer labId) {
        return labFacade.getLab(labId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "lab/create")
    public void createLab(@RequestBody final LabData labData) {
        labFacade.createLab(labData);
    }

}
