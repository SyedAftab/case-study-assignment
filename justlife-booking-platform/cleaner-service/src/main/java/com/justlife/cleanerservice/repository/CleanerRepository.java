package com.justlife.cleanerservice.repository;

import com.justlife.cleanerservice.domain.Cleaner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aftab
 */
public interface CleanerRepository extends JpaRepository<Cleaner, Long> {

    List<Cleaner> findByVehicleId(Long vehicleId);
}
