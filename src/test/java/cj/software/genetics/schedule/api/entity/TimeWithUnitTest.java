package cj.software.genetics.schedule.api.entity;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TimeWithUnitTest {

    @Test
    void implementsSerializable() {
        Class<?>[] interfaces = TimeWithUnit.class.getInterfaces();
        assertThat(interfaces).as("interfaces").contains(Serializable.class);
    }

    @Test
    void constructEmpty()
            throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        TimeWithUnit.Builder builder = TimeWithUnit.builder();
        assertThat(builder).as("builder").isNotNull();

        Field field = builder.getClass().getDeclaredField("instance");

        Object instanceBefore = field.get(builder);
        assertThat(instanceBefore).as("instance in builder before build").isNotNull().isInstanceOf(
                TimeWithUnit.class);

        TimeWithUnit instance = builder.build();
        assertThat(instance).as("built instance").isNotNull();

        Object instanceAfter = field.get(builder);
        assertThat(instanceAfter).as("instance in builder after build").isNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getTime()).as("time").isNull();
        softy.assertThat(instance.getUnit()).as("unit").isNull();
        softy.assertAll();
    }

    @Test
    void constructFilled() {
        Integer time = -1;
        TimeUnit unit = TimeUnit.MINUTES;
        TimeWithUnit instance = TimeWithUnit.builder()
                .withTime(time)
                .withUnit(unit)
                .build();
        assertThat(instance).as("built instance").isNotNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getTime()).as("value").isEqualTo(time);
        softy.assertThat(instance.getUnit()).as("unit").isEqualTo(unit);
        softy.assertAll();
    }

    @Test
    void defaultIsValid() {
        TimeWithUnit instance = new TimeWithUnitBuilder().build();
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<TimeWithUnit>> violations = validator.validate(instance);
            assertThat(violations).as("constraint violations").isEmpty();
        }
    }

    @Test
    void toDuration13Minutes() {
        TimeWithUnit instance = new TimeWithUnitBuilder().build();
        assertDuration(instance, Duration.ofMinutes(13));
    }

    @Test
    void toDuration96Hourse() {
        TimeWithUnit instance = TimeWithUnit.builder().withTime(96).withUnit(TimeUnit.HOURS).build();
        assertDuration(instance, Duration.ofHours(96));
    }

    private void assertDuration(TimeWithUnit instance, Duration expDuration) {
        Duration duration = instance.toDuration();
        assertThat(duration).isEqualTo(expDuration);
    }

    @Test
    void stringRepresentation() {
        TimeWithUnit instance = new TimeWithUnitBuilder().build();
        String asString = instance.toString();
        assertThat(asString).isEqualTo("13 MINUTES");
    }

    @Test
    void equalObject() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().build();
        assertThat(instance1).isEqualTo(instance2);
    }

    @Test
    void equalHashes() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void unequalValue() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().withTime(123423).build();
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void unequalValueHashes() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().withTime(123423).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void unequalUnit() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().withUnit(TimeUnit.MINUTES).build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().withUnit(TimeUnit.SECONDS).build();
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void unequalUnitHashes() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().withUnit(TimeUnit.MINUTES).build();
        TimeWithUnit instance2 = new TimeWithUnitBuilder().withUnit(TimeUnit.SECONDS).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void differentObject() {
        TimeWithUnit instance1 = new TimeWithUnitBuilder().build();
        Object instance2 = "I'm a String";
        assertThat(instance1).isNotEqualTo(instance2);
    }
}