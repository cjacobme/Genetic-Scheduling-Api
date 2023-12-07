package cj.software.genetics.schedule.api.entity;

public class FitnessBuilder extends Fitness.Builder {
    public FitnessBuilder() {
        super.withDurationInSeconds(5.0).withFitnessValue(0.2);
    }
}
