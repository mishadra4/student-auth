package com.md.sa.facade.impl;

import com.md.sa.facade.UserFacade;
import com.md.sa.facade.converter.GenericConverter;
import com.md.sa.facade.converter.UserConverter;
import com.md.sa.facade.dto.ChangePasswordRequestData;
import com.md.sa.facade.dto.UserData;
import com.md.sa.model.User;
import com.md.sa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private GenericConverter<User, UserData> userConverter;

    @Override
    public UserData getUser(String username) {
        return userConverter.convertToDTO(userService.getUser(username));
    }

    @Override
    public void changePassword(final ChangePasswordRequestData userData) {
        userService.changePassword(userData);
    }

    @Override
    public UserData authenticate() {
        return null;
    }


}
