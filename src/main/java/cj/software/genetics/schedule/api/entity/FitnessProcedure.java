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
     * durations:
     *
     * <table style="border: 1px solid black;">
     *     <tr>
     *         <th style="border: 1px solid black;">Priority</th>
     *         <th style="border: 1px solid black;">duration</th>
     *         <th style="border: 1px solid black;">scaling factor</th>
     *         <th style="border: 1px solid black;">operation</th>
     *         <th style="border: 1px solid black;">squared value</th>
     *     </tr>
     *     <tr>
     *         <td style="border: 1px solid black;">1</td>
     *         <td style="border: 1px solid black;">40 s</td>
     *         <td style="border: 1px solid black;">3</td>
     *         <td style="border: 1px solid black;">40 * 40 * 3</td>
     *         <td style="border: 1px solid black;">4800</td>
     *     </tr>
     *     <tr>
     *         <td style="border: 1px solid black;">2</td>
     *         <td style="border: 1px solid black;">70 s</td>
     *         <td style="border: 1px solid black;">2</td>
     *         <td style="border: 1px solid black;">70 * 70 * 2</td>
     *         <td style="border: 1px solid black;">9800</td>
     *     </tr>
     *     <tr>
     *         <td style="border: 1px solid black;">3</td>
     *         <td style="border: 1px solid black;">35 s</td>
     *         <td style="border: 1px solid black;">1</td>
     *         <td style="border: 1px solid black;">35 * 35</td>
     *         <td style="border: 1px solid black;">1225</td>
     *     </tr>
     * </table>
     * <p>
     * The sum of all the values in the last column is 15825. The square root of that is 125.7975
     */
    SQUARED
}
