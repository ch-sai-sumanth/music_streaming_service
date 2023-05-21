package com.geekster.musicstreamingapi.repository;

import com.geekster.musicstreamingapi.model.PlayList;
import com.geekster.musicstreamingapi.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayListRepository extends JpaRepository<PlayList,Integer> {


}
