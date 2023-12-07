package cj.software.genetics.schedule.api.entity;

/**
 * defines how the fitness value of a solution is calculated
 */
public enum FitnessProcedure {
    /**
     * the fitness is calculated as 1.0 divided by the duration of the latest worker.
     */
    LATEST,

    /**
     * the fitness value is calculated as 1.0 divided by the standard deviation of the durations of all workers
     */
    STD_DEVIATION
}
