package cj.software.genetics.schedule.api.entity;

import java.time.temporal.ChronoUnit;

public enum TimeUnit {

    DAYS(ChronoUnit.DAYS),
    HOURS(ChronoUnit.HOURS),
    MINUTES(ChronoUnit.MINUTES),
    SECONDS(ChronoUnit.SECONDS);

    private final ChronoUnit chronoUnit;

    TimeUnit(ChronoUnit chronoUnit) {
        this.chronoUnit = chronoUnit;
    }

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }
}
