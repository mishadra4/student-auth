package com.md.sa.rest;

import com.md.sa.facade.SubjectFacade;
import com.md.sa.facade.dto.SubjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SubjectsController {

    @Autowired
    private SubjectFacade subjectFacade;

    @RequestMapping("/lecturer/{username}/subjects")
    public List<SubjectData> getSubjects(@PathVariable final String username) {
        return subjectFacade.getSubjects(username);
    }

    @RequestMapping("/subjects/{subjectId}")
    public SubjectData getSubject(@PathVariable final String subjectId) {
        return subjectFacade.getSubject(subjectId);
    }

}
