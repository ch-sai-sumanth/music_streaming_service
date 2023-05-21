package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String userEmail);
}
