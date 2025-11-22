package com.justlife.bookingservice.client;

import java.time.LocalDate;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aftab
 */
@FeignClient(name = "cleaner-service")
public interface CleanerClient {

    @GetMapping("/api/cleaners/availability")
    List<Long> getAvailableCleanerIds(
            @RequestParam("date") LocalDate date,
            @RequestParam("durationHours") int durationHours,
            @RequestParam("cleanersCount") int cleanersCount
    );
}
