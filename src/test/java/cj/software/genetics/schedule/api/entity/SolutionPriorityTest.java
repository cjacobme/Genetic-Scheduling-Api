package cj.software.genetics.schedule.api.entity;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionPriorityTest extends ValidatingTest {

    @Test
    void implementsSerializable() {
        Class<?>[] interfaces = SolutionPriority.class.getInterfaces();
        assertThat(interfaces).as("interfaces").contains(Serializable.class, Comparable.class);
    }

    @Test
    void constructEmpty()
            throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        SolutionPriority.Builder builder = SolutionPriority.builder();
        assertThat(builder).as("builder").isNotNull();

        Field field = builder.getClass().getDeclaredField("instance");

        Object instanceBefore = field.get(builder);
        assertThat(instanceBefore).as("instance in builder before build").isNotNull().isInstanceOf(
                SolutionPriority.class);

        SolutionPriority instance = builder.build();
        assertThat(instance).as("built instance").isNotNull();

        Object instanceAfter = field.get(builder);
        assertThat(instanceAfter).as("instance in builder after build").isNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getValue()).as("priority value").isNull();
        softy.assertThat(instance.getTasks()).as("tasks").isEmpty();
        softy.assertAll();
    }

    @Test
    void constructFilled() {
        Integer value = 13;
        SortedMap<Integer, Task> tasks = new TreeMap<>();
        tasks.put(13, new TaskBuilder().withIdentifier(1).build());
        tasks.put(1234, new TaskBuilder().withIdentifier(2).build());
        SolutionPriority instance = SolutionPriority.builder()
                .withValue(value)
                .withTasks(tasks)
                .build();
        assertThat(instance).as("built instance").isNotNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getValue()).as("value").isEqualTo(value);
        softy.assertThat(instance.getTasks()).as("tasks").isEqualTo(tasks);
        softy.assertAll();
    }

    @Test
    void defaultIsValid() {
        SolutionPriority instance = new SolutionPriorityBuilder().build();
        validate(instance);
    }

    @Test
    void equalObjects() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        SolutionPriority instance2 = new SolutionPriorityBuilder().withTasks(null).build();
        assertThat(instance1).isEqualTo(instance2);
    }

    @Test
    void equalHashes() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        SolutionPriority instance2 = new SolutionPriorityBuilder().withTasks(null).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void unequalValue() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        SolutionPriority instance2 = new SolutionPriorityBuilder().withValue(1234543).build();
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void unequalHashes() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        SolutionPriority instance2 = new SolutionPriorityBuilder().withValue(1234543).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void otherObject() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        Object instance2 = 3.14;
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void order() {
        SolutionPriority instance1 = new SolutionPriorityBuilder().build();
        SolutionPriority instance2 = new SolutionPriorityBuilder().withValue(33).build();
        int order = instance1.compareTo(instance2);
        assertThat(order).isNegative();
    }

    @Test
    void stringRepresentation() {
        SolutionPriority instance = new SolutionPriorityBuilder().build();
        String asString = instance.toString();
        assertThat(asString).as("String representation").isEqualTo("SolutionPriority[value=1]");
    }
}