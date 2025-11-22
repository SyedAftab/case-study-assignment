package com.justlife.vehicleservice.api;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Aftab
 */
public record VehicleRequest(
        @NotBlank
        String name,
        @NotBlank
        String licensePlate,
        Boolean active) {

}
