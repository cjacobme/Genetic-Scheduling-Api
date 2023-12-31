package cj.software.genetics.schedule.api.entity;

import cj.software.genetics.schedule.api.validation.AllTasksDispatchedOnce;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllTasksDispatchedOnce
public class Solution implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(0)
    private Integer generationStep;

    @NotNull
    @Min(0)
    private Integer indexInPopulation;

    @NotNull(groups = FitnessCalculated.class)
    @Valid
    private Fitness fitness;

    @NotEmpty
    private final List<@NotNull @Valid Worker> workers = new ArrayList<>();

    private Solution() {
    }

    public Integer getGenerationStep() {
        return generationStep;
    }

    public Integer getIndexInPopulation() {
        return indexInPopulation;
    }

    public List<Worker> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    @Override
    public boolean equals(Object otherObject) {
        boolean result;
        if (otherObject instanceof Solution other) {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(this.indexInPopulation, other.indexInPopulation)
                    .append(this.generationStep, other.generationStep);
            result = builder.build();
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder()
                .append(indexInPopulation)
                .append(generationStep);
        int result = builder.build();
        return result;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("generation step", generationStep)
                .append("index", indexInPopulation);
        String result = builder.build();
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected Solution instance;

        protected Builder() {
            instance = new Solution();
        }

        public Solution build() {
            Solution result = instance;
            instance = null;
            return result;
        }

        public Builder withGenerationStep(Integer generationStep) {
            instance.generationStep = generationStep;
            return this;
        }

        public Builder withIndexInPopulation(Integer indexInPopulation) {
            instance.indexInPopulation = indexInPopulation;
            return this;
        }

        public Builder withWorkers(List<Worker> workers) {
            instance.workers.clear();
            if (workers != null) {
                instance.workers.addAll(workers);
            }
            return this;
        }
    }
}