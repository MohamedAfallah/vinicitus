package es.madrid.vicinatus.bookings.repositories;


import es.madrid.vicinatus.bookings.entities.Booking;
import es.madrid.vicinatus.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByItemOwner(User owner);
    List<Booking> findByItem(Long itemId);
}
