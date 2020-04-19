package com.md.sa.facade.converter;

import com.md.sa.facade.dto.UserData;
import com.md.sa.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter implements GenericConverter<User, UserData> {
    @Override
    public User convertToEntity(UserData dto) {
        return null;
    }

    @Override
    public UserData convertToDTO(User entity) {
        UserData userData = new UserData();
        userData.setUsername(entity.getUsername());
        userData.setFirstName(entity.getFirstName());
        userData.setLastName(entity.getLastName());
        userData.setRoles(entity.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return userData;
    }
}
