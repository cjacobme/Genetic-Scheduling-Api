package cj.software.genetics.schedule.api.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

public class SolutionSetup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(1)
    private Integer solutionCount;

    @NotNull
    @Min(1)
    private Integer workersPerSolutionCount;

    @NotNull
    private FitnessProcedure fitnessProcedure;

    private SolutionSetup() {
    }

    public Integer getSolutionCount() {
        return solutionCount;
    }

    public Integer getWorkersPerSolutionCount() {
        return workersPerSolutionCount;
    }

    public FitnessProcedure getFitnessProcedure() {
        return fitnessProcedure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected SolutionSetup instance;

        protected Builder() {
            instance = new SolutionSetup();
        }

        public SolutionSetup build() {
            SolutionSetup result = instance;
            instance = null;
            return result;
        }

        public Builder withSolutionCount(Integer solutionCount) {
            instance.solutionCount = solutionCount;
            return this;
        }

        public Builder withWorkersPerSolutionCount(Integer workersPerSolutionCount) {
            instance.workersPerSolutionCount = workersPerSolutionCount;
            return this;
        }

        public Builder withFitnessProcedure(FitnessProcedure fitnessProcedure) {
            instance.fitnessProcedure = fitnessProcedure;
            return this;
        }
    }
}