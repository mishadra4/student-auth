package com.md.sa.facade;

import com.md.sa.facade.dto.ChangePasswordRequestData;
import com.md.sa.facade.dto.UserData;

public interface UserFacade {

    UserData authenticate();

    UserData getUser(String username);

    void changePassword(ChangePasswordRequestData userData);
}
