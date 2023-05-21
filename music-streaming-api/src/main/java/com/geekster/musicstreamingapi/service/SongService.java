package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    ISongRepository songRepository;

    public void addSong(Song song) {
        songRepository.save(song);
    }

    public void deleteSongById(Integer id) {
        boolean isSongExist=songRepository.existsById(id);

        if(isSongExist)
        {
            songRepository.deleteById(id);
        }
        else
        {
            throw new IllegalStateException("song not present to delete");
        }

    }

    public Optional<Song> getSongById(Integer id) {
        return songRepository.findById(id);
    }

    public void updateSongById(Integer id,Song song) {

        boolean isSongExist=songRepository.existsById(id);

        if(isSongExist)
        {
            songRepository.save(song);
        }
        else
        {
            throw new IllegalStateException("song not present to update");
        }
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
