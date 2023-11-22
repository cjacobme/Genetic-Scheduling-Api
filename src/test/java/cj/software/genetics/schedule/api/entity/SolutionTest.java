package cj.software.genetics.schedule.api.entity;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest extends ValidatingTest {

    @Test
    void implementsSerializable() {
        Class<?>[] interfaces = Solution.class.getInterfaces();
        assertThat(interfaces).as("interfaces").contains(Serializable.class);
    }

    @Test
    void constructEmpty()
            throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        Solution.Builder builder = Solution.builder();
        assertThat(builder).as("builder").isNotNull();

        Field field = builder.getClass().getDeclaredField("instance");

        Object instanceBefore = field.get(builder);
        assertThat(instanceBefore).as("instance in builder before build").isNotNull().isInstanceOf(
                Solution.class);

        Solution instance = builder.build();
        assertThat(instance).as("built instance").isNotNull();

        Object instanceAfter = field.get(builder);
        assertThat(instanceAfter).as("instance in builder after build").isNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getGenerationStep()).as("generation step").isNull();
        softy.assertThat(instance.getIndexInPopulation()).as("index in population").isNull();
        softy.assertThat(instance.getFitnessValue()).as("fitness value").isNull();
        softy.assertThat(instance.getWorkers()).as("workers").isEmpty();
        softy.assertAll();
    }

    @Test
    void constructFilled() {
        Integer generationStep = -1;
        Integer indexInGeneration = -2;
        double fitnessValue = -3.14;
        List<Worker> workers = List.of(new WorkerBuilder().build(), new WorkerBuilder().build());
        Solution instance = Solution.builder()
                .withGenerationStep(generationStep)
                .withIndexInPopulation(indexInGeneration)
                .withFitnessValue(fitnessValue)
                .withWorkers(workers)
                .build();
        assertThat(instance).as("built instance").isNotNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getGenerationStep()).as("generation step").isEqualTo(generationStep);
        softy.assertThat(instance.getIndexInPopulation()).as("index in generation").isEqualTo(indexInGeneration);
        softy.assertThat(instance.getFitnessValue()).as("fitness value").isEqualTo(fitnessValue);
        softy.assertThat(instance.getWorkers()).as("workers").isEqualTo(workers);
        softy.assertAll();
    }

    @Test
    void defaultIsValid() {
        Solution instance = new SolutionBuilder().build();
        validate(instance);
    }

    @Test
    void equalObjects() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withFitnessValue(34445.6).withWorkers(null).build();
        assertThat(instance1).isEqualTo(instance2);
    }

    @Test
    void equalHashes() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withFitnessValue(34445.6).withWorkers(null).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void unequalGenerationStep() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withGenerationStep(16).build();
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void unequalHashesGenerationStep() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withGenerationStep(16).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void unequalIndexInPopulation() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withIndexInPopulation(34).build();
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void unequalHashesIndexInPopulation() {
        Solution instance1 = new SolutionBuilder().build();
        Solution instance2 = new SolutionBuilder().withIndexInPopulation(34).build();
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void unequalObject() {
        Solution instance1 = new SolutionBuilder().build();
        Object instance2 = "other object";
        assertThat(instance1).isNotEqualTo(instance2);
    }

    @Test
    void stringRepresentation() {
        Solution instance = new SolutionBuilder().build();
        String asString = instance.toString();
        assertThat(asString).as("String representation").isEqualTo("Solution[generation step=15,index=33]");
    }
}