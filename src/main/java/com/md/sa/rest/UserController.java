package com.md.sa.rest;

import com.md.sa.facade.UserFacade;
import com.md.sa.facade.dto.ChangePasswordRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "user/password/change", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody @Valid ChangePasswordRequestData userData) {
        userFacade.changePassword(userData);
        return ResponseEntity.ok().build();
    }

}
