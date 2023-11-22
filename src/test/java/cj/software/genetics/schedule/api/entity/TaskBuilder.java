package cj.software.genetics.schedule.api.entity;

import java.util.SortedMap;
import java.util.TreeMap;

import static cj.software.genetics.schedule.api.entity.TimeUnit.SECONDS;

public class TaskBuilder extends Task.Builder {
    public static SortedMap<Integer, Task> create(int startInclusive, int endExclusive) {
        SortedMap<Integer, Task> result = new TreeMap<>();
        int slot = 0;
        for (int index = startInclusive; index < endExclusive; index++) {
            result.put(slot, new TaskBuilder().withIdentifier(index).build());
            slot += 3;
        }
        return result;
    }

    public TaskBuilder() {
        super
                .withIdentifier(4243)
                .withDuration(TimeWithUnit.builder().withTime(10).withUnit(SECONDS).build());
    }
}
