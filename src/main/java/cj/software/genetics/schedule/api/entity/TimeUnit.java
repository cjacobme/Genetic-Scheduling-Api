package cj.software.genetics.schedule.api.entity;

import java.time.temporal.ChronoUnit;

public enum TimeUnit {

    DAYS(ChronoUnit.DAYS, 86400),
    HOURS(ChronoUnit.HOURS, 3600),
    MINUTES(ChronoUnit.MINUTES, 60),
    SECONDS(ChronoUnit.SECONDS, 1);

    private final ChronoUnit chronoUnit;

    private final int secondsFactor;

    TimeUnit(ChronoUnit chronoUnit, int secondsFactor) {
        this.chronoUnit = chronoUnit;
        this.secondsFactor = secondsFactor;
    }

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }

    public int getSecondsFactor() {
        return secondsFactor;
    }
}
