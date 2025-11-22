package com.justlife.bookingservice.api;

import com.justlife.bookingservice.api.CreateBookingRequest;
import com.justlife.bookingservice.api.UpdateBookingRequest;
import com.justlife.bookingservice.domain.Booking;
import com.justlife.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aftab
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody @Valid CreateBookingRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> get(@PathVariable Long id) {
        Booking b = service.get(id);
        return b != null ? ResponseEntity.ok(b) : ResponseEntity.notFound().build();
    }

    // LIST with pagination
    @GetMapping
    public Page<Booking> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.list(page, size);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Long id,
            @RequestBody @Valid UpdateBookingRequest req) {
        Booking updated = service.update(id, req);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
