package com.jobhuntinger.job.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneJobDescriptionValidator.class)
@Documented
public @interface AtLeastOneJobDescription {

    String message() default "At least one description (url or text)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
