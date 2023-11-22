package cj.software.genetics.schedule.api.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private TimeWithUnit duration;

    @NotNull
    private Integer identifier;

    private Task() {
    }

    public TimeWithUnit getDuration() {
        return duration;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", this.identifier)
                .append("duration", this.duration);
        String result = builder.build();
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        boolean result;
        if (otherObject instanceof Task other) {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(this.identifier, other.identifier);
            result = builder.build();
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder()
                .append(this.identifier);
        int result = builder.build();
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected Task instance;

        protected Builder() {
            instance = new Task();
        }

        public Task build() {
            Task result = instance;
            instance = null;
            return result;
        }

        public Builder withIdentifier(Integer identifier) {
            instance.identifier = identifier;
            return this;
        }

        public Builder withDuration(TimeWithUnit duration) {
            instance.duration = duration;
            return this;
        }
    }
}