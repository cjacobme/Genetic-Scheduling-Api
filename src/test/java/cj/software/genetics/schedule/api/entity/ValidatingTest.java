package cj.software.genetics.schedule.api.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ValidatingTest {
    private static ValidatorFactory factory;

    private static Validator validator;

    @BeforeAll
    static void createValidation() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void shutdownValidation() {
        factory.close();
    }

    public <T> void validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
        assertThat(violations).as("constraint violations").isEmpty();
    }
}
