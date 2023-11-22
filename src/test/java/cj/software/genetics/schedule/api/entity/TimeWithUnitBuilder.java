package cj.software.genetics.schedule.api.entity;

public class TimeWithUnitBuilder extends TimeWithUnit.Builder {
    public TimeWithUnitBuilder() {
        super.withTime(13).withUnit(TimeUnit.MINUTES);
    }
}
