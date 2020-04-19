package com.md.sa.facade.validator;


import com.md.sa.facade.dto.ChangePasswordRequestData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // initialization
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        String password = "";
        String matchingPassword = "";
        if (obj instanceof ChangePasswordRequestData) {
            ChangePasswordRequestData changePasswordRequestData = (ChangePasswordRequestData) obj;
            password = changePasswordRequestData.getPassword();
            matchingPassword = changePasswordRequestData.getMatchingPassword();
        }
        return password.equals(matchingPassword);
    }
}