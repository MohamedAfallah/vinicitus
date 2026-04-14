package es.madrid.vicinatus.bookings.controllers;

import es.madrid.vicinatus.bookings.BookingStatus;
import es.madrid.vicinatus.bookings.entities.Booking;
import es.madrid.vicinatus.bookings.services.BookingService;
import es.madrid.vicinatus.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PatchMapping("/{id}/status")
    public Booking updateStatus(
            @PathVariable Long id,
            @RequestParam BookingStatus status,
            @RequestParam Long requesterId) {
        return bookingService.updateBookingStatus(id, status, requesterId);
    }

    @GetMapping("/my-requests")
    public List<Booking> getMyRequests(@RequestBody User user) {
        return bookingService.getBookingsIPresented(user);
    }

    @GetMapping("/my-items-bookings")
    public List<Booking> getIncomingBookings(@RequestBody User owner) {
        return bookingService.getBookingsReceivedAsOwner(owner);
    }
}
