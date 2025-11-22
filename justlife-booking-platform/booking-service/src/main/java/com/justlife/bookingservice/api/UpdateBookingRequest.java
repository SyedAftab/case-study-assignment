package com.justlife.bookingservice.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author Aftab
 */

public record UpdateBookingRequest(
        @NotNull
        LocalDateTime startTime,
        @Positive
        int durationHours,
        @Positive
        int cleanersCount,
        Set<Long> cleanerIds) {

}
