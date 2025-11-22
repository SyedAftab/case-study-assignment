package com.justlife.vehicleservice.repository;

import com.justlife.vehicleservice.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aftab
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
