package com.geekster.musicstreamingapi.controller;

import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.service.AuthTokenService;
import com.geekster.musicstreamingapi.service.PlayedSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playedSongs")
public class PlayedSongsController {

    @Autowired
    AuthTokenService tokenService;

    @Autowired
    PlayedSongsService playedSongsService;
    // user ,playlist,song

    @PostMapping("{playlistID}/{songID}")
    public void addToPlayedSongs(@PathVariable String email,@PathVariable String token,@PathVariable Integer playlistID,@PathVariable Integer songID)
    {
            if(tokenService.authenticate(email,token))
            {
                playedSongsService.addToPlayedSongs(playlistID,songID);
            }
    }

    @GetMapping("userId/{userId}")
    public List<Song> getAllPlayedSongs(@PathVariable Integer userId)
    {
        return playedSongsService.getAllPlayedSongsById(userId);
    }
}
