package es.madrid.vicinatus.bookings.services;

import es.madrid.vicinatus.bookings.BookingStatus;
import es.madrid.vicinatus.bookings.entities.Booking;
import es.madrid.vicinatus.bookings.repositories.BookingRepository;
import es.madrid.vicinatus.users.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService (BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking){
        List<Booking> bookingListByItem = bookingRepository.findByItem(booking.getItem().getId());
        for (Booking existing : bookingListByItem) {
            if (booking.getStartDate().isBefore(existing.getEndDate()) &&
                    booking.getEndDate().isAfter(existing.getStartDate())) {
                throw new RuntimeException("El objeto ya está reservado en esas fechas");
            }
        }

        if(booking.getBooker().getId().equals(booking.getItem().getOwner().getId())){
            throw new RuntimeException("El dueño del mismo item no puede alquilar el objeto");
        }

        long days = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        if (days <= 0) days = 1;
        booking.setTotalPrice(days * booking.getItem().getPricePerDay());

        double karma = booking.getBooker().getKarma();
        if(karma >= 4.5){
            booking.setNotesToOwner("¡Usuario excelente! Este vecino tiene un Karma muy alto.");
        } else if (karma >= 2.5) {
            booking.setNotesToOwner("Atención: El Karma de este vecino es medio.");
        } else {
            booking.setNotesToOwner("Atención: El Karma de este vecino es bajo.");
        }

        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long bookingId, BookingStatus newStatus, Long requesterId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        BookingStatus actual = booking.getStatus();

        if (newStatus == BookingStatus.CANCELLED) {
            if (!booking.getBooker().getId().equals(requesterId)) {
                throw new RuntimeException("Solo quien hizo la reserva puede cancelarla");
            }
        } else {
            if (!booking.getItem().getOwner().getId().equals(requesterId)) {
                throw new RuntimeException("Solo el dueño del objeto puede gestionar esta reserva");
            }
        }

        if (actual == BookingStatus.COMPLETED || actual == BookingStatus.CANCELLED) {
            throw new RuntimeException("La reserva ya está finalizada y no se puede modificar");
        }

        if (actual == BookingStatus.REJECTED && newStatus == BookingStatus.ACCEPTED) {
            throw new RuntimeException("No se puede aceptar algo ya rechazado");
        }

        if (actual == BookingStatus.ACTIVE && newStatus != BookingStatus.COMPLETED) {
            throw new RuntimeException("Una reserva activa solo puede pasar a completada");
        }

        if (newStatus == BookingStatus.COMPLETED) {
            LocalDate today = LocalDate.now();
            if (today.isAfter(booking.getEndDate())) {
                User booker = booking.getBooker();
                booker.setKarma(Math.max(0.0, booker.getKarma() - 0.5));
            }
        }

        booking.setStatus(newStatus);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsIPresented(User user){
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getBookingsReceivedAsOwner(User owner){
        return bookingRepository.findByItemOwner(owner);
    }
}