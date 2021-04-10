package com.fieryrider.tmdbclone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumNameValidator extends EnumNameBaseValidator implements ConstraintValidator<EnumNameValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        return Arrays.stream(this.values).map(Enum::name).collect(Collectors.toList()).contains(value);
    }
}
