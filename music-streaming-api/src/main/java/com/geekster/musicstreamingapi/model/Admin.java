package com.geekster.musicstreamingapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_music_admin")
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(nullable = false)
    @NotEmpty
    private String adminName;

    @Email(regexp = "[A-Za-z0-9]+@admin\\.com")
    private String adminEmail;

    @NotEmpty
    private String adminPassword;
}
