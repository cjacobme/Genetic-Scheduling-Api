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
     * the fitness value is calculated as 1.0 divided by the squared sum. If we for example 3 priorities with these
     * durations 40s, 70s, and 35s. We use a descending scaling factor, starting with the number of priorities. So
     * for prio 1 we calculate 40 * 40 * 3 = 4800. For priority 2 it is 70 * 70 * 2 = 9800. For priority 3 it is
     * 35 * 35 = 1225. The sum of all the values is 15825. The square root of that is 125.7975
     */
    SQUARED
}
