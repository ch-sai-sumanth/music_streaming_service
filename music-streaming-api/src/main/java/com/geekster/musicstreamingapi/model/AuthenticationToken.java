package com.geekster.musicstreamingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    private LocalDateTime tokenCreationDate;

    private String token;

    @OneToOne
    private User user;

    public AuthenticationToken(User user) {
        this.tokenCreationDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }
}
