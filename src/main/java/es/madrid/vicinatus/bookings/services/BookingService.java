package es.madrid.vicinatus.bookings.services;

import es.madrid.vicinatus.bookings.BookingStatus;
import es.madrid.vicinatus.bookings.entities.Booking;
import es.madrid.vicinatus.bookings.repositories.BookingRepository;
import es.madrid.vicinatus.users.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService (BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking){
        List<Booking> bookingListByItem = bookingRepository.findByItem(booking.getItem().getId());
        for (int i = 0; i < bookingListByItem.size(); i++) {
            Booking existing = bookingListByItem.get(i);
            if (booking.getStartDate().isBefore(existing.getEndDate()) &&
                    booking.getEndDate().isAfter(existing.getStartDate())) {
                throw new RuntimeException("El objeto ya está reservado en esas fechas");
            }
        }

        if(booking.getBooker().getId().equals(booking.getItem().getOwner().getId())){
            throw new RuntimeException("El dueño del mismo item no puede alquilar el objeto");
        }
        if(booking.getBooker().getKarma() >= 4.5){
            booking.setNotesToOwner("⭐¡Usuario excelente! Este vecino tiene un Karma muy alto.");
        }else{
            booking.setNotesToOwner("Atención: El Karma de este vecino es bajo.");
        }

        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }


    public List<Booking> getMyBookings(User booker){
        return bookingRepository.findByBooker(booker);
    }
}
