package cj.software.genetics.schedule.api.entity;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static cj.software.genetics.schedule.api.entity.TimeUnit.DAYS;
import static cj.software.genetics.schedule.api.entity.TimeUnit.MINUTES;
import static cj.software.genetics.schedule.api.entity.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

class SchedulingProblemTest extends ValidatingTest {

    @Test
    void implementsSerializable() {
        Class<?>[] interfaces = SchedulingProblem.class.getInterfaces();
        assertThat(interfaces).as("interfaces").contains(Serializable.class);
    }

    @Test
    void constructEmpty()
            throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        SchedulingProblem.Builder builder = SchedulingProblem.builder();
        assertThat(builder).as("builder").isNotNull();

        Field field = builder.getClass().getDeclaredField("instance");

        Object instanceBefore = field.get(builder);
        assertThat(instanceBefore).as("instance in builder before build").isNotNull().isInstanceOf(
                SchedulingProblem.class);

        SchedulingProblem instance = builder.build();
        assertThat(instance).as("built instance").isNotNull();

        Object instanceAfter = field.get(builder);
        assertThat(instanceAfter).as("instance in builder after build").isNull();
        assertThat(instance.getPriorities()).isEmpty();
    }

    @Test
    void constructFilled() {
        ProblemPriority prio1 = new ProblemPriorityBuilder().withValue(1).build();
        ProblemPriority prio2 = new ProblemPriorityBuilder().withValue(2).build();
        Collection<ProblemPriority> priorities = List.of(prio2, prio1);
        SchedulingProblem instance = SchedulingProblem.builder()
                .withPriorities(priorities)
                .build();
        assertThat(instance).as("built instance").isNotNull();
        assertThat(instance.getPriorities()).containsExactly(prio1, prio2);
    }

    @Test
    void defaultIsValid() {
        SchedulingProblem instance = new SchedulingProblemBuilder().build();
        validate(instance);
    }

    @Test
    void readFromJson() throws IOException {
        try (InputStream is = Objects.requireNonNull(SchedulingProblemTest.class.getResourceAsStream("SchedulingProblem.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            SchedulingProblem loaded = objectMapper.readValue(is, SchedulingProblem.class);
            validate(loaded);
            SchedulingProblem expected = SchedulingProblem.builder()
                    .withPriorities(List.of(
                            ProblemPriority.builder()
                                    .withValue(1)
                                    .withTasks(List.of(
                                            Task.builder().withIdentifier(1).withDuration(TimeWithUnit.builder().withTime(10).withUnit(SECONDS).build()).build(),
                                            Task.builder().withIdentifier(2).withDuration(TimeWithUnit.builder().withTime(20).withUnit(SECONDS).build()).build(),
                                            Task.builder().withIdentifier(3).withDuration(TimeWithUnit.builder().withTime(1).withUnit(MINUTES).build()).build()
                                    ))
                                    .build(),
                            ProblemPriority.builder()
                                    .withValue(2)
                                    .withTasks(List.of(
                                            Task.builder().withIdentifier(101).withDuration(TimeWithUnit.builder().withTime(15).withUnit(SECONDS).build()).build(),
                                            Task.builder().withIdentifier(102).withDuration(TimeWithUnit.builder().withTime(1).withUnit(DAYS).build()).build()
                                    ))
                                    .build()
                    )).build();
            assertThat(loaded).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}