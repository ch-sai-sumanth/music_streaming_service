package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.model.PlayList;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.repository.IPlayListRepository;
import com.geekster.musicstreamingapi.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayListService {

    @Autowired
    IPlayListRepository playListRepository;

    @Autowired
    ISongRepository songRepository;

    public String createPlaylist(PlayList myPlaylist)
    {
        playListRepository.save(myPlaylist);
        return "Playlist saved";
    }

    public String deletePlaylistById(Integer id) {
        playListRepository.deleteById(id);
        return "playlist deleted";
    }

    public String addSongInToPlaylist(Integer playlistID,Integer songID) {

        //we have the playlist Id , verify playlist present or not
        boolean isPlaylistExist=playListRepository.existsById(playlistID);
        if(!isPlaylistExist)
            throw new IllegalStateException("Not a valid playlist");

        //verify song is present or not
        boolean isSongExist=songRepository.existsById(songID);
        if(!isSongExist)
            throw new IllegalStateException("Not a valid song");

        Song originlSong=songRepository.findById(songID).get();

        PlayList myPlaylist=playListRepository.findById(playlistID).get();

        originlSong.setPlayList(myPlaylist);

        songRepository.save(originlSong);
        return "song added in to playlist succesfully";
    }

    public String deleteSongFromPlaylist(Integer playListID, Integer songId) {

        //we have the playlist Id , verify playlist present or not
        boolean isPlaylistExist=playListRepository.existsById(playListID);
        if(!isPlaylistExist)
            throw new IllegalStateException("Not a valid playlist");

        //verify song is present in playlist or not
        Song song1=songRepository.findById(songId).get();

        PlayList playList=song1.getPlayList();

        if(playList==null)
            throw new IllegalStateException("there is no song in this playList");

        //setting play list to null so that this song no longer present in any playlist
       song1.setPlayList(null);
        songRepository.save(song1);

        return "song deleted from playlist succesfully";
    }

    public void addSongsToPlaylist(Integer playlistID, Integer songID) {

        Song song=songRepository.findById(songID).get();
        PlayList myPlaylist=playListRepository.findById(playlistID).get();

        song.setPlayList(myPlaylist);

        songRepository.save(song);

    }
}
