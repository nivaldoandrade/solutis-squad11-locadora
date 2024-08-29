package com.squad11.locadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cadastro_pendente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CadastroPendente {

    @Id
    private Long id;

    @Column(nullable = false)
    private String token;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(nullable = false, updatable = false)
    private  LocalDateTime expiresAt;

    public CadastroPendente(Long id, String token, LocalDateTime expiresAt) {
        this.id = id;
        this.token = token;
        this.expiresAt = expiresAt;
    }
}
