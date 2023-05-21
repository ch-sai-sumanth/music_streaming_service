package com.geekster.musicstreamingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayedSongs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_playedsongs_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "fk_playedsongs_playlist")
    private PlayList playList;

    @OneToOne
    @JoinColumn(name = "fk_playedsongs_song")
    private Song song;
}
