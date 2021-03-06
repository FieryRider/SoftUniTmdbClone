package com.fieryrider.tmdbclone.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { NullOrNotBlankValidator.class } )
public @interface NullOrNotBlank {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
