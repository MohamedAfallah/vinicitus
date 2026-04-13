package es.madrid.vicinatus.users;


import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @Author : Mohamed Afallah
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Double latitude;
    private Double longitude;

    private String neighborhood;

    @Builder.Default
    private Double karma = 5.0;

}
