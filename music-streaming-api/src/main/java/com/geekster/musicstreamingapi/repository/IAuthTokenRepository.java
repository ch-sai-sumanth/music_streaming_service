package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByToken(String token);
}
