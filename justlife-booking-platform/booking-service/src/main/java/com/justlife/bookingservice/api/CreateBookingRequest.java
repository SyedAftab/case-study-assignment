package com.justlife.bookingservice.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

public record CreateBookingRequest(
        @NotNull
        Long customerId,
        @NotNull
        Long vehicleId,
        @NotNull
        LocalDateTime startTime,
        @Positive
        int durationHours,
        @Positive
        int cleanersCount,
        Set<Long> cleanerIds) {

}
