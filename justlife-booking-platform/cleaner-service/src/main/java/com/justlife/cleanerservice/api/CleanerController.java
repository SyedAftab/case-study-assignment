package com.justlife.cleanerservice.api;

import com.justlife.cleanerservice.api.CleanerRequest;
import com.justlife.cleanerservice.domain.Cleaner;
import com.justlife.cleanerservice.repository.CleanerRepository;
import com.justlife.cleanerservice.service.CleanerAvailabilityService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aftab
 */
@RestController
@RequestMapping("/api/cleaners")
public class CleanerController {

    private final CleanerRepository repository;
    private final CleanerAvailabilityService availabilityService;

    public CleanerController(CleanerRepository repository,
            CleanerAvailabilityService availabilityService) {
        this.repository = repository;
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public ResponseEntity<Cleaner> create(@RequestBody @Valid CleanerRequest req) {
        Cleaner c = new Cleaner();
        c.setFullName(req.fullName());
        c.setVehicleId(req.vehicleId());
        c.setActive(req.active() != null ? req.active() : true);
        return ResponseEntity.ok(repository.save(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cleaner> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------------------------------
    // LIST (Pagination)
    // Example: GET /api/cleaners?page=0&size=10
    // ----------------------------------------------------------------------
    @GetMapping
    public Page<Cleaner> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    // ----------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Cleaner> update(@PathVariable Long id,
            @RequestBody @Valid CleanerRequest req) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setFullName(req.fullName());
                    existing.setVehicleId(req.vehicleId());
                    if (req.active() != null) {
                        existing.setActive(req.active());
                    }
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------------------------------------------------------------
    // AVAILABILITY ENDPOINT
    // ----------------------------------------------------------------------
    @GetMapping("/availability")
    public List<Long> getAvailability(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int durationHours,
            @RequestParam int cleanersCount
    ) {
        return availabilityService.findAvailableCleanerIds(date, durationHours, cleanersCount);
    }
}
