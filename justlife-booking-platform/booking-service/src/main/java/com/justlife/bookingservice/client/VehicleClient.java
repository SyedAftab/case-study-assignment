package com.justlife.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Aftab
 */
@FeignClient(name = "vehicle-service")
public interface VehicleClient {

    @GetMapping("/api/vehicles/{id}")
    Object getVehicle(@PathVariable("id") Long id);
}
