package com.justlife.vehicleservice.api;

import com.justlife.vehicleservice.api.VehicleRequest;
import com.justlife.vehicleservice.domain.Vehicle;
import com.justlife.vehicleservice.repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aftab
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleRepository repository;

    public VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody @Valid VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(request.name());
        vehicle.setLicensePlate(request.licensePlate());
        vehicle.setActive(request.active() != null ? request.active() : true);
        return ResponseEntity.ok(repository.save(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------------------------------
    // LIST with Pagination
    // Example: /api/vehicles?page=0&size=10
    // ----------------------------------------------------------------------
    @GetMapping
    public Page<Vehicle> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id,
            @RequestBody @Valid VehicleRequest request) {

        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setLicensePlate(request.licensePlate());
                    if (request.active() != null) {
                        existing.setActive(request.active());
                    }
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
