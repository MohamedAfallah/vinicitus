package es.madrid.vicinatus;

import es.madrid.vicinatus.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
    }
}
