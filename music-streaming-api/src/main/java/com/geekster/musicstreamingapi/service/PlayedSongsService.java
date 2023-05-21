package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.model.PlayList;
import com.geekster.musicstreamingapi.model.PlayedSongs;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.model.User;
import com.geekster.musicstreamingapi.repository.IPlayListRepository;
import com.geekster.musicstreamingapi.repository.IPlayedSongsRepository;
import com.geekster.musicstreamingapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayedSongsService {

    @Autowired
    IPlayedSongsRepository playedSongsRepository;

    @Autowired
    IPlayListRepository playListRepository;

    @Autowired
    IUserRepository userRepository;

    public void addToPlayedSongs(Integer playlistID, Integer songID) {

        PlayList myplaylist=playListRepository.findById(playlistID).get();
        Song song=myplaylist.getSong().get(songID);
        User user=myplaylist.getUser();


        PlayedSongs playedSongs=new PlayedSongs();

        //setting detials to playedsongs
        playedSongs.setPlayList(myplaylist);
        playedSongs.setSong(song);
        playedSongs.setUser(user);

        playedSongsRepository.save(playedSongs);
    }

    public List<Song> getAllPlayedSongsById(Integer userId) {

        User user=userRepository.findById(userId).get();

        return playedSongsRepository.findAllByUser(user);
    }
}
