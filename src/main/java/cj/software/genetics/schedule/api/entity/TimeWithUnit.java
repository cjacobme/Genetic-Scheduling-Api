package cj.software.genetics.schedule.api.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static cj.software.genetics.schedule.api.entity.TimeUnit.*;

public class TimeWithUnit implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer time;

    @NotNull
    private TimeUnit unit;

    private TimeWithUnit() {
    }

    /**
     * copy constructor
     */
    public TimeWithUnit(TimeWithUnit source) {
        this.time = source.getTime();
        this.unit = source.getUnit();
    }

    public static TimeWithUnit ofDays(int days) {
        TimeWithUnit result = TimeWithUnit.builder().withTime(days).withUnit(DAYS).build();
        return result;
    }

    public static TimeWithUnit ofHours(int hours) {
        TimeWithUnit result = TimeWithUnit.builder().withTime(hours).withUnit(HOURS).build();
        return result;
    }

    public static TimeWithUnit ofMinutes(int minutes) {
        TimeWithUnit result = TimeWithUnit.builder().withTime(minutes).withUnit(MINUTES).build();
        return result;
    }

    public static TimeWithUnit ofSeconds(int seconds) {
        TimeWithUnit result = TimeWithUnit.builder().withTime(seconds).withUnit(SECONDS).build();
        return result;
    }

    public Integer getTime() {
        return time;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Duration toDuration() {
        ChronoUnit chronoUnit = unit.getChronoUnit();
        Duration result = Duration.of(time, chronoUnit);
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        boolean result;
        if (otherObject instanceof TimeWithUnit other) {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(this.time, other.time)
                    .append(this.unit, other.unit);
            result = builder.build();
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder()
                .append(time)
                .append(unit);
        int result = builder.build();
        return result;
    }

    @Override
    public String toString() {
        String result = String.format("%d %s", time, unit);
        return result;
    }

    public static class Builder {
        protected TimeWithUnit instance;

        protected Builder() {
            instance = new TimeWithUnit();
        }

        public TimeWithUnit build() {
            TimeWithUnit result = instance;
            instance = null;
            return result;
        }

        public Builder withTime(Integer time) {
            instance.time = time;
            return this;
        }

        public Builder withUnit(TimeUnit unit) {
            instance.unit = unit;
            return this;
        }
    }
}