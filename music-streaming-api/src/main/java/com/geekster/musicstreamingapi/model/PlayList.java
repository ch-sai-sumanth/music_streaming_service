package com.geekster.musicstreamingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;

    private String playlistName;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Song> song;

}
