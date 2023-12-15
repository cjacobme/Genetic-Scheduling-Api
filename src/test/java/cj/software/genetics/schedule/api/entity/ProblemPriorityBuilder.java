package cj.software.genetics.schedule.api.entity;

import java.util.Set;

import static cj.software.genetics.schedule.api.entity.TimeUnit.MINUTES;
import static cj.software.genetics.schedule.api.entity.TimeUnit.SECONDS;

public class ProblemPriorityBuilder extends ProblemPriority.Builder {
    public ProblemPriorityBuilder() {
        super.withValue(1)
                .withTasks(Set.of(
                        new TaskBuilder().withIdentifier(1).build(),
                        new TaskBuilder().withIdentifier(2).withDuration(TimeWithUnit.builder().withTime(20).withUnit(SECONDS).build()).build(),
                        new TaskBuilder().withIdentifier(3).withDuration(TimeWithUnit.builder().withTime(1).withUnit(MINUTES).build()).build()));
    }
}
