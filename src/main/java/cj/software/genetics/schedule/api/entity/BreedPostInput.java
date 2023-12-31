package cj.software.genetics.schedule.api.entity;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

public class BreedPostInput implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(1)
    private Integer numSteps;

    @NotNull
    @Min(0)
    private Integer elitismCount;

    @NotNull
    @Min(2)
    private Integer tournamentSize;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double mutationRate;

    @NotNull
    @Valid
    private Population population;

    @NotNull
    private FitnessProcedure fitnessProcedure;

    private BreedPostInput() {
    }

    public Integer getNumSteps() {
        return numSteps;
    }

    public Integer getElitismCount() {
        return elitismCount;
    }

    public Integer getTournamentSize() {
        return tournamentSize;
    }

    public Double getMutationRate() {
        return mutationRate;
    }

    public Population getPopulation() {
        return population;
    }

    public FitnessProcedure getFitnessProcedure() {
        return fitnessProcedure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected BreedPostInput instance;

        protected Builder() {
            instance = new BreedPostInput();
        }

        public BreedPostInput build() {
            BreedPostInput result = instance;
            instance = null;
            return result;
        }

        public Builder withNumSteps(Integer numSteps) {
            instance.numSteps = numSteps;
            return this;
        }

        public Builder withElitismCount(Integer elitismCount) {
            instance.elitismCount = elitismCount;
            return this;
        }

        public Builder withTournamentSize(Integer tournamentSize) {
            instance.tournamentSize = tournamentSize;
            return this;
        }

        public Builder withMutationRate(Double mutationRate) {
            instance.mutationRate = mutationRate;
            return this;
        }

        public Builder withPopulation(Population population) {
            instance.population = population;
            return this;
        }

        public Builder withFitnessProcedure(FitnessProcedure fitnessProcedure) {
            instance.fitnessProcedure = fitnessProcedure;
            return this;
        }
    }
}