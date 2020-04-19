package com.md.sa.service;

import com.md.sa.facade.dto.UserData;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtTokenService {

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(UserData userDetails);

    Date getExpirationDateFromToken(String token);

    String getUsernameFromToken(String token);

}
