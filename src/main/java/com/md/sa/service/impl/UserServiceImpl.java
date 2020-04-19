package com.md.sa.service.impl;

import com.md.sa.facade.dto.ChangePasswordRequestData;
import com.md.sa.model.User;
import com.md.sa.repository.UserRepository;
import com.md.sa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(final String username) {
        return userRepository.findById(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void changePassword(final ChangePasswordRequestData userData) {
        final User user = userRepository.findById(userData.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User wasn`t found"));
        if (!passwordEncoder.matches(userData.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password isn`t correct");
        }
        final String newEncodedPassword = passwordEncoder.encode(userData.getPassword());
        user.setPassword(newEncodedPassword);
        userRepository.save(user);
    }
}
