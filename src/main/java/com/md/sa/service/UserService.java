package com.md.sa.service;

import com.md.sa.facade.dto.ChangePasswordRequestData;
import com.md.sa.model.User;

public interface UserService {

    User getUser(String username);

    void changePassword(ChangePasswordRequestData userData);
}
