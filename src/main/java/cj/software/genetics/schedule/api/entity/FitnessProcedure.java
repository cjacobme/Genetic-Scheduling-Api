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
    STD_DEVIATION,

    /**
     * the fitness value is calculated as 1.0 divided by the squared sum. If we for example 3 priorities, we calculate
     * the duration of each priority and square them. We then add all squared values. From that sum, we take the square
     * root. The fitness value is then the 1.0 divided by this square root.
     */
    SQUARED
}
