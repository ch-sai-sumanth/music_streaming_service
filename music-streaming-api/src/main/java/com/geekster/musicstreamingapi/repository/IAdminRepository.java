package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByAdminEmail(String adminEmail);

    boolean existsByAdminEmail(String adminEmail);
}
