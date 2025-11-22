package com.justlife.cleanerservice.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aftab
 */
@Service
public class CleanerAvailabilityService {

    public List<Long> findAvailableCleanerIds(LocalDate date,
            int durationHours,
            int cleanersCount) {
        // Simplified stub. Real implementation would check existing bookings.
        return List.of();
    }
}
