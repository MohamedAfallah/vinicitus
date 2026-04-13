package es.madrid.vicinatus.bookings.services;

import es.madrid.vicinatus.bookings.entities.Booking;
import es.madrid.vicinatus.bookings.repositories.BookingRepository;
import es.madrid.vicinatus.catalog.Item;
import es.madrid.vicinatus.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void shoudThrowExceptionWhenOwnerTriesToBookOwnItem(){
        User owner = new User();
        owner.setId(1L);

        Item item = new Item();
        item.setOwner(owner);

        Booking newBooking = new Booking();
        newBooking.setBooker(owner);
        newBooking.setItem(item);

        assertThrows(RuntimeException.class, () ->{
            bookingService.createBooking(newBooking);
        });
    }

    @Test
    void sendNoteToOwnerBasedOnBookerKarmaRanking() {
        User booker = new User();
        booker.setId(2L);
        booker.setKarma(1.5);

        User owner = new User();
        owner.setId(1L);

        Item item = new Item();
        item.setId(10L);
        item.setOwner(owner);

        Booking newBooking = new Booking();
        newBooking.setBooker(booker);
        newBooking.setItem(item);
        newBooking.setStartDate(LocalDate.now());
        newBooking.setEndDate(LocalDate.now().plusDays(1));

        when(bookingRepository.findByItem(10L)).thenReturn(List.of());
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        Booking result = bookingService.createBooking(newBooking);

        assertEquals("Atención: El Karma de este vecino es bajo.", result.getNotesToOwner());
    }

}
