package com.md.sa.rest;

import com.md.sa.facade.UserFacade;
import com.md.sa.facade.dto.UserData;
import com.md.sa.rest.jwt.JwtRequest;
import com.md.sa.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "api/authenticate", method = RequestMethod.POST)
    public ResponseEntity createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserData user = userFacade.getUser(authenticationRequest.getUsername());
        final String token = jwtTokenService.generateToken(user);
        user.setToken(token);
        return ResponseEntity.ok(user);
    }

    private void authenticate(final String username, final String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }

}
