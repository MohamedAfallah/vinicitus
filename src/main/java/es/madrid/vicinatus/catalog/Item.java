package es.madrid.vicinatus.catalog;

import es.madrid.vicinatus.users.User;
import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @Author : Mohamed Afallah
 */
@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Double pricePerDay;

    @Column(nullable = false)
    private String category;

    @Column
    private String imageURL;

    @Column
    @Builder.Default
    private Boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
}
