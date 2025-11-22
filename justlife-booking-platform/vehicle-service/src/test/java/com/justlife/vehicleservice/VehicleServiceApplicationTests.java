package com.justlife.vehicleservice;

import com.justlife.vehicleservice.domain.Vehicle;
import com.justlife.vehicleservice.repository.VehicleRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Aftab
 */
@SpringBootTest
class VehicleServiceApplicationTests {

    @Autowired
    VehicleRepository repository;

    @Test
    void saveVehicle() {
        Vehicle v = new Vehicle();
        v.setName("Test Vehicle");
        v.setLicensePlate("TEST-123");
        Vehicle saved = repository.save(v);
        assertThat(saved.getId()).isNotNull();
    }
}
