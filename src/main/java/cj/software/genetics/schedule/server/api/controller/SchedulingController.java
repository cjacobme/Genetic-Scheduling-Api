package cj.software.genetics.schedule.server.api.controller;

import cj.software.genetics.schedule.server.api.entity.SchedulingCreatePostInput;
import cj.software.genetics.schedule.server.api.entity.SchedulingCreatePostOutput;
import cj.software.genetics.schedule.server.api.exception.SlotOccupiedException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface SchedulingController {
    @NotNull
    @Valid SchedulingCreatePostOutput create (SchedulingCreatePostInput input) throws SlotOccupiedException;
}
