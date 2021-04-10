package com.fieryrider.tmdbclone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class EnumNameCollectionValidator extends EnumNameBaseValidator implements ConstraintValidator<EnumNameValid, Collection<String>> {
    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        return Arrays.stream(this.values).map(Enum::name).collect(Collectors.toList()).containsAll(value);
    }
}
