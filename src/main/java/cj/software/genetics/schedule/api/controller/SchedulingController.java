package cj.software.genetics.schedule.api.controller;

import cj.software.genetics.schedule.api.entity.BreedPostInput;
import cj.software.genetics.schedule.api.entity.BreedPostOutput;
import cj.software.genetics.schedule.api.entity.SchedulingCreatePostInput;
import cj.software.genetics.schedule.api.entity.SchedulingCreatePostOutput;
import cj.software.genetics.schedule.api.exception.SlotOccupiedException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface SchedulingController {
    @NotNull
    @Valid
    SchedulingCreatePostOutput create(
            @NotNull
            @Valid
            SchedulingCreatePostInput input) throws SlotOccupiedException;

    @NotNull
    @Valid
    BreedPostOutput breed(
            @NotNull
            @Valid
            BreedPostInput breedPostInput) throws SlotOccupiedException;
}
