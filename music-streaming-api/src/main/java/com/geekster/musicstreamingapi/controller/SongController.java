package com.geekster.musicstreamingapi.controller;

import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.repository.IAdminRepository;
import com.geekster.musicstreamingapi.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @Autowired
    IAdminRepository adminRepository;

    @PostMapping("/add")
    public void addSong(@RequestParam String adminEmail, @RequestBody Song song)
    {
        boolean isValidAdmin=adminRepository.existsByAdminEmail(adminEmail);

        if(isValidAdmin)
        {
            songService.addSong(song);
        }
        else
        {
            throw new IllegalStateException("enter correct admin details");
        }


    }

    @DeleteMapping("/delete/{id}")
    public void deleteSongById(@RequestParam String adminEmail, @PathVariable Integer id)
    {

        boolean isValidAdmin=adminRepository.existsByAdminEmail(adminEmail);

        if(isValidAdmin)
        {
            songService.deleteSongById(id);
        }
        else
        {
            throw new IllegalStateException("enter correct admin details");
        }
    }

    @GetMapping("/songId/{id}")
    public Optional<Song> getSongById(@RequestParam String adminEmail,@PathVariable Integer id)
    {

        boolean isValidAdmin=adminRepository.existsByAdminEmail(adminEmail);

        if(isValidAdmin)
        {
            return songService.getSongById(id);
        }
        else
        {
            throw new IllegalStateException("enter correct admin details");
        }
    }

    @GetMapping("/getAllSongs")
    public List<Song> getAllSongs(@RequestParam String adminEmail)
    {
        boolean isValidAdmin=adminRepository.existsByAdminEmail(adminEmail);

        if(isValidAdmin)
        {
            return songService.getAllSongs();
        }
        else
        {
            throw new IllegalStateException("enter correct admin details");
        }
    }

    @PutMapping("/update/{id}")
    public void updateSongById(@RequestParam String adminEmail,@PathVariable Integer id,@RequestBody Song song)
    {
        boolean isValidAdmin=adminRepository.existsByAdminEmail(adminEmail);

        if(isValidAdmin)
        {
            songService.updateSongById(id,song);
        }
        else
        {
            throw new IllegalStateException("enter correct admin details");
        }
    }


}
