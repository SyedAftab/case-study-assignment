package com.justlife.bookingservice;

import com.justlife.bookingservice.api.CreateBookingRequest;
import com.justlife.bookingservice.client.CustomerClient;
import com.justlife.bookingservice.client.VehicleClient;
import com.justlife.bookingservice.domain.Booking;
import com.justlife.bookingservice.repository.BookingRepository;
import com.justlife.bookingservice.service.BookingService;
import java.time.LocalDateTime;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Aftab
 */
class BookingServiceTests {

    private BookingRepository bookingRepository;
    private CustomerClient customerClient;
    private VehicleClient vehicleClient;
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        bookingRepository = mock(BookingRepository.class);
        customerClient = mock(CustomerClient.class);
        vehicleClient = mock(VehicleClient.class);
        bookingService = new BookingService(bookingRepository, customerClient, vehicleClient);
    }

    @Test
    void rejectsFridayBooking() {
        LocalDateTime fridayMorning = LocalDateTime.of(2025, 1, 3, 10, 0); // Assume Friday
        CreateBookingRequest req = new CreateBookingRequest(1L, 1L, fridayMorning, 2, 1, Set.of(1L));
        assertThatThrownBy(() -> bookingService.create(req))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Friday");
    }

    @Test
    void acceptsValidBooking() {
        LocalDateTime mondayMorning = LocalDateTime.of(2025, 1, 6, 10, 0);
        CreateBookingRequest req = new CreateBookingRequest(1L, 1L, mondayMorning, 2, 1, Set.of(1L));

        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
            Booking b = invocation.getArgument(0);
            b.setId(100L);
            return b;
        });

        Booking b = bookingService.create(req);
        assertThat(b.getId()).isEqualTo(100L);
        verify(customerClient).getCustomer(1L);
        verify(vehicleClient).getVehicle(1L);
    }
}
