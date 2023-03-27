package io.ara.remittance.model;

import io.ara.remittance.security.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "sender")
@Data
@Builder
public class SenderEntity extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "is_active")
    private Boolean isActive;

//    @OneToOne(fetch = FetchType.EAGER, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinColumn(name = "verificationToken_id", nullable = false, referencedColumnName = "id")
//    private VerificationToken verificationToken;
}
