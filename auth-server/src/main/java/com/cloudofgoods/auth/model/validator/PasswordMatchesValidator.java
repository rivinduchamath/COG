package com.cloudofgoods.auth.model.validator;

import com.cloudofgoods.auth.model.request.UserRegister;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegister user = (UserRegister) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
