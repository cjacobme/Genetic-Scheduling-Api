package cj.software.genetics.schedule.server.api.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

public class BreedPostOutput implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private Population population;

    private BreedPostOutput() {
    }

    public Population getPopulation() {
        return population;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected BreedPostOutput instance;

        protected Builder() {
            instance = new BreedPostOutput();
        }

        public BreedPostOutput build() {
            BreedPostOutput result = instance;
            instance = null;
            return result;
        }

        public Builder withPopulation(Population population) {
            instance.population = population;
            return this;
        }
    }
}