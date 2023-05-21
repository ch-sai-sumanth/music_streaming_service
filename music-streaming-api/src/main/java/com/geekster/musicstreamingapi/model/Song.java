package com.geekster.musicstreamingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    private String title;

    private String artist;

    private String album;

    private Genre genre;

    private String duration;

    private LocalDate releaseDate;

    @ManyToOne
    private PlayList playList;
}
