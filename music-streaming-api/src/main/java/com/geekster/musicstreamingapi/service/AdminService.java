package com.geekster.musicstreamingapi.service;

import com.geekster.musicstreamingapi.model.Admin;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.repository.IAdminRepository;
import com.geekster.musicstreamingapi.repository.ISongRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    IAdminRepository adminRepository;


    @Autowired
    ISongRepository songRepository;

    public String addSong(Song song) {

        boolean isSongPresent=songRepository.existsById(song.getSongId());
        if(isSongPresent)
            throw new IllegalStateException("Song already exist!, please try to add new songs..");

        songRepository.save(song);
        return "song added succesfully";
    }

    public Optional<Song> getSongById(Integer id) {
        return songRepository.findById(id);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public String removeSong(Integer id) {
        boolean isSongPresent=songRepository.existsById(id);
        if(!isSongPresent)
            throw new IllegalStateException("you can't delete the song which is not present");

        songRepository.deleteById(id);
        return "song deleted succesfully";
    }

    public String updateSong(Integer id, Song song) {

        boolean isSongPresent=songRepository.existsById(song.getSongId());

        if(!isSongPresent)
            throw new IllegalStateException("Invalid song ID");

        else
        {
            Song originalSong=songRepository.findById(id).get();

            if(song.getSongId()!=null)
                originalSong.setSongId(song.getSongId());
            if(song.getTitle()!=null)
                originalSong.setTitle(song.getTitle());
            if(song.getArtist()!=null)
                originalSong.setArtist(song.getArtist());
            if(song.getGenre()!=null)
                originalSong.setGenre(song.getGenre());
            if(song.getDuration()!=null)
                originalSong.setDuration(song.getDuration());
            if(song.getReleaseDate()!=null)
                originalSong.setReleaseDate(song.getReleaseDate());

            songRepository.save(originalSong);
        }
        return "song details updated succesfully";
    }

    public String add(Admin newAdmin) {

        Admin admin=adminRepository.findByAdminEmail(newAdmin.getAdminEmail());

        if(admin!=null)
            throw new IllegalStateException("Admin already exist!!..you can't add Admin again");

        String encryptedPassword=null;

        try
        {
            encryptedPassword=encryptPassword(newAdmin.getAdminPassword());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        newAdmin.setAdminPassword(encryptedPassword);

        adminRepository.save(newAdmin);
        return "Admin added succesfully";
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;

    }
}
