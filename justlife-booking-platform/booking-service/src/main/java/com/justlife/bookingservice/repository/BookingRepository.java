package com.justlife.bookingservice.repository;

import com.justlife.bookingservice.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aftab
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
