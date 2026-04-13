package es.madrid.vicinatus.bookings.entities;

import es.madrid.vicinatus.bookings.BookingStatus;
import es.madrid.vicinatus.catalog.Item;
import es.madrid.vicinatus.users.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * @Author : Mohamed Afallah
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booker_id")
    private User booker;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    @Column(nullable = false)
    private String notesToOwner;
}