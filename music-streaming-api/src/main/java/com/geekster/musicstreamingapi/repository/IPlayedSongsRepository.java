package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.PlayedSongs;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayedSongsRepository extends JpaRepository<PlayedSongs,Integer> {
    List<Song> findAllByUser(User user);
}
