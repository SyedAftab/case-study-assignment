package com.justlife.bookingservice.service;

import com.justlife.bookingservice.api.CreateBookingRequest;
import com.justlife.bookingservice.api.UpdateBookingRequest;
import com.justlife.bookingservice.client.CustomerClient;
import com.justlife.bookingservice.client.VehicleClient;
import com.justlife.bookingservice.domain.Booking;
import com.justlife.bookingservice.repository.BookingRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aftab
 */
@Service
public class BookingService {

    private static final LocalTime START_WORK = LocalTime.of(8, 0);
    private static final LocalTime END_WORK = LocalTime.of(22, 0);

    private final BookingRepository repo;
    private final CustomerClient customerClient;
    private final VehicleClient vehicleClient;

    public BookingService(BookingRepository repo,
            CustomerClient customerClient,
            VehicleClient vehicleClient) {
        this.repo = repo;
        this.customerClient = customerClient;
        this.vehicleClient = vehicleClient;
    }

    // ------------------------------ CREATE ------------------------------
    @Transactional
    public Booking create(CreateBookingRequest req) {
        validateRequest(req.startTime(), req.durationHours(), req.cleanersCount());

        customerClient.getCustomer(req.customerId());
        vehicleClient.getVehicle(req.vehicleId());

        Booking booking = new Booking();
        booking.setCustomerId(req.customerId());
        booking.setVehicleId(req.vehicleId());
        booking.setStartTime(req.startTime());
        booking.setEndTime(req.startTime().plusHours(req.durationHours()));
        booking.setDurationHours(req.durationHours());
        booking.setCleanersCount(req.cleanersCount());
        booking.setCleanerIds(req.cleanerIds());

        return repo.save(booking);
    }

    // ------------------------------ READ by ID ------------------------------
    public Booking get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ------------------------------ PAGINATION ------------------------------
    public Page<Booking> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    // ------------------------------ UPDATE ------------------------------
    @Transactional
    public Booking update(Long id, UpdateBookingRequest req) {
        return repo.findById(id).map(existing -> {

            validateRequest(req.startTime(), req.durationHours(), req.cleanersCount());

            existing.setStartTime(req.startTime());
            existing.setEndTime(req.startTime().plusHours(req.durationHours()));
            existing.setDurationHours(req.durationHours());
            existing.setCleanersCount(req.cleanersCount());
            existing.setCleanerIds(req.cleanerIds());

            return repo.save(existing);

        }).orElse(null);
    }

    // ------------------------------ DELETE ------------------------------
    @Transactional
    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    // ------------------------------ VALIDATION ------------------------------
    private void validateRequest(LocalDateTime start, int durationHours, int cleanersCount) {

        if (start.getDayOfWeek() == DayOfWeek.FRIDAY) {
            throw new IllegalArgumentException("Booking not allowed on Fridays");
        }

        LocalTime startTime = start.toLocalTime();
        if (startTime.isBefore(START_WORK)) {
            throw new IllegalArgumentException("Start time must be >= 08:00");
        }

        if (startTime.plusHours(durationHours).isAfter(END_WORK)) {
            throw new IllegalArgumentException("End time must be <= 22:00");
        }

        if (durationHours != 2 && durationHours != 4) {
            throw new IllegalArgumentException("Duration must be 2 or 4 hours");
        }

        if (cleanersCount < 1 || cleanersCount > 3) {
            throw new IllegalArgumentException("Cleaners count must be 1–3");
        }
    }
}
