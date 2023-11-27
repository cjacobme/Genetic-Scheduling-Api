package cj.software.genetics.schedule.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AllTasksDispatchedOnceValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllTasksDispatchedOnce {

    String message() default "Solution %s Task %s at worker %d prio %d slot %d, other position: worker=%d, prio=%d, slot=%d";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
