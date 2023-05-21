package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song,Integer> {
}
