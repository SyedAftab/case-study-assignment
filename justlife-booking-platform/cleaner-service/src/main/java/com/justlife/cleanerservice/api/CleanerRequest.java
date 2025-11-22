package com.justlife.cleanerservice.api;

/**
 *
 * @author Aftab
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CleanerRequest(
        @NotBlank
        String fullName,
        @NotNull
        Long vehicleId,
        Boolean active) {

}
