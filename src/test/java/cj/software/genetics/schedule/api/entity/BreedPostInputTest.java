package cj.software.genetics.schedule.api.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static cj.software.genetics.schedule.api.entity.TimeUnit.MINUTES;
import static cj.software.genetics.schedule.api.entity.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

class BreedPostInputTest extends ValidatingTest {
    @Test
    void implementsSerializable() {
        Class<?>[] interfaces = BreedPostInput.class.getInterfaces();
        assertThat(interfaces).as("interfaces").contains(Serializable.class);
    }

    @Test
    void constructEmpty()
            throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        BreedPostInput.Builder builder = BreedPostInput.builder();
        assertThat(builder).as("builder").isNotNull();

        Field field = builder.getClass().getDeclaredField("instance");

        Object instanceBefore = field.get(builder);
        assertThat(instanceBefore).as("instance in builder before build").isNotNull().isInstanceOf(
                BreedPostInput.class);

        BreedPostInput instance = builder.build();
        assertThat(instance).as("built instance").isNotNull();

        Object instanceAfter = field.get(builder);
        assertThat(instanceAfter).as("instance in builder after build").isNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getNumSteps()).as("number of steps").isNull();
        softy.assertThat(instance.getElitismCount()).as("elitism count").isNull();
        softy.assertThat(instance.getTournamentSize()).as("tournament size").isNull();
        softy.assertThat(instance.getMutationRate()).as("mutation rate").isNull();
        softy.assertThat(instance.getPopulation()).as("population").isNull();
        softy.assertThat(instance.getFitnessProcedure()).as("fitness procedure").isNull();
        softy.assertAll();
    }

    @Test
    void constructFilled() {
        Integer numSteps = -1;
        Integer elitismCount = -3;
        Integer tournamentSize = -4;
        Double mutationRate = -1.2;
        Population population = Population.builder().build();
        FitnessProcedure fitnessProcedure = FitnessProcedure.LATEST;

        BreedPostInput instance = BreedPostInput.builder()
                .withNumSteps(numSteps)
                .withElitismCount(elitismCount)
                .withTournamentSize(tournamentSize)
                .withMutationRate(mutationRate)
                .withPopulation(population)
                .withFitnessProcedure(fitnessProcedure)
                .build();

        assertThat(instance).as("built instance").isNotNull();
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(instance.getNumSteps()).as("num steps").isEqualTo(numSteps);
        softy.assertThat(instance.getElitismCount()).as("elitism count").isEqualTo(elitismCount);
        softy.assertThat(instance.getTournamentSize()).as("tournament size").isEqualTo(tournamentSize);
        softy.assertThat(instance.getMutationRate()).as("mutation rate").isEqualTo(mutationRate);
        softy.assertThat(instance.getPopulation()).as("population").isSameAs(population);
        softy.assertThat(instance.getFitnessProcedure()).as("fitness procedure").isEqualTo(fitnessProcedure);
        softy.assertAll();
    }

    @Test
    void defaultIsValid() {
        BreedPostInput instance = new BreedPostInputBuilder().build();
        validate(instance);
    }

    @Test
    void loadFromJson() throws IOException {
        try (InputStream is = Objects.requireNonNull(BreedPostInputTest.class.getResourceAsStream("BreedPostInput.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            BreedPostInput instance = objectMapper.readValue(is, BreedPostInput.class);
            validate(instance);
            BreedPostInput expected = createExpectedFromJson();
            assertThat(instance)
                    .as("read from json")
                    .usingRecursiveComparison()
                    .ignoringFields("population.solutions[0].workers[0].asMap")
                    .isEqualTo(expected);
        }
    }

    private BreedPostInput createExpectedFromJson() {
        SortedMap<Integer, Task> tasks = new TreeMap<>();
        tasks.put(2, Task.builder().withDuration(TimeWithUnit.builder().withTime(2).withUnit(MINUTES).build()).withIdentifier(32).build());
        tasks.put(25, Task.builder().withDuration(TimeWithUnit.builder().withTime(30).withUnit(SECONDS).build()).withIdentifier(4).build());
        Collection<SolutionPriority> priorities = List.of(
                SolutionPriority.builder()
                        .withValue(1)
                        .withTasks(tasks)
                        .build());
        Worker worker = Worker.builder()
                .withPriorities(priorities)
                .build();
        worker.asMap.clear();
        List<Worker> workers = List.of(worker);
        List<Solution> solutions = List.of(
                Solution.builder()
                        .withGenerationStep(2)
                        .withIndexInPopulation(3)
                        .withWorkers(workers)
                        .build());
        Population population = Population.builder()
                .withGenerationStep(3)
                .withSolutions(solutions)
                .build();
        BreedPostInput result = BreedPostInput.builder()
                .withFitnessProcedure(FitnessProcedure.STD_DEVIATION)
                .withNumSteps(20)
                .withElitismCount(7)
                .withTournamentSize(5)
                .withMutationRate(0.15)
                .withPopulation(population)
                .build();
        return result;
    }
}