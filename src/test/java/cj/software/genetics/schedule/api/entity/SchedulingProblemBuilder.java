package cj.software.genetics.schedule.api.entity;

import java.util.List;

import static cj.software.genetics.schedule.api.entity.TimeUnit.DAYS;

public class SchedulingProblemBuilder extends SchedulingProblem.Builder {
    public SchedulingProblemBuilder() {
        super.withPriorities(List.of(
                new ProblemPriorityBuilder().withValue(1).build(),
                new ProblemPriorityBuilder().withValue(15).withSlotCount(30).withTasks(List.of(
                        Task.builder().withDuration(TimeWithUnit.builder().withTime(15).withUnit(DAYS).build()).withIdentifier(414141).build(),
                        new TaskBuilder().withIdentifier(12345).build()
                )).build()
        ));
    }
}
