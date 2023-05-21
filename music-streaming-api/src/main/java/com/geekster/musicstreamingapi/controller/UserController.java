package com.geekster.musicstreamingapi.controller;

import com.geekster.musicstreamingapi.dto.SignInInput;
import com.geekster.musicstreamingapi.dto.SignInOutput;
import com.geekster.musicstreamingapi.dto.SignUpInput;
import com.geekster.musicstreamingapi.dto.SignUpOutput;
import com.geekster.musicstreamingapi.model.PlayList;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.model.User;
import com.geekster.musicstreamingapi.service.AuthTokenService;
import com.geekster.musicstreamingapi.service.PlayListService;
import com.geekster.musicstreamingapi.service.SongService;
import com.geekster.musicstreamingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthTokenService tokenService;

    @Autowired
    PlayListService playListService;

    @Autowired
    SongService songService;


    @GetMapping("/songId/{id}")
    public Optional<Song> getSongById(@PathVariable Integer id)
    {
            return songService.getSongById(id);
    }

    @GetMapping("/getAllSongs")
    public List<Song> getAllSongs()
    {
            return songService.getAllSongs();
    }
    @PostMapping("/signup")
    public SignUpOutput signup(@RequestBody SignUpInput singUpDto)
    {
        return userService.signup(singUpDto);
    }

    @PostMapping("/singin")
    public SignInOutput signin(@RequestBody SignInInput signInDto)
    {
        return userService.signin(signInDto);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String email, @RequestParam String token, @RequestBody User user)
    {
        String messege=null;
        HttpStatus status;

        //if email and token are valid
        if(tokenService.authenticate(email,token))
        {
            try
            {
                userService.updateUser(user,token);
                status=HttpStatus.OK;
                messege="user updated succesfully";
            }
            catch(Exception e)
            {
                status=HttpStatus.BAD_REQUEST;
                messege="Enter valid information";
            }
        }

        else
        {
            status=HttpStatus.FORBIDDEN;
            messege="Invalid User";
        }

        return new ResponseEntity<String>(messege,status);
    }


    //create playlist
    @PostMapping("/createPlaylist")
    public String createPlaylist(@RequestParam String email,@RequestParam String token,@RequestBody PlayList myplaylist)
    {

        if(tokenService.authenticate(email,token))
        {
            return playListService.createPlaylist(myplaylist);
        }
       return "You are not valid user to create playlist,enter correct details or sign up instead";

    }

    @PostMapping("/addSongToPlaylist/{playlistID}/{songID}")
    public void addSongToPlaylist(@RequestParam String email,@RequestParam String token,@PathVariable Integer playlistID,@PathVariable Integer songID)
    {
        if(tokenService.authenticate(email,token))
        {
            playListService.addSongsToPlaylist(playlistID,songID);
        }
    }

    //delete playlist
    @DeleteMapping("/deletePlaylist/{id}")
    public String deletePlaylist(@RequestParam String email,@RequestParam String token,@PathVariable Integer id)
    {

        if(tokenService.authenticate(email,token))
        {
            return playListService.deletePlaylistById(id);
        }
        return "You are not valid user to delete playlist,enter correct details or sign up instead";
    }


    //remove songs from playlist
    @DeleteMapping("deleteSongFromPlaylist/{playListID}/{songId}")
    public String deleteSongFromPlaylist(@RequestParam String email, @RequestParam String token,@PathVariable Integer playlistID,@PathVariable Integer songId)
    {

        if(tokenService.authenticate(email,token))
        {
            return playListService.deleteSongFromPlaylist(playlistID,songId);
        }
        return "You are not valid user to delete song from playlist,enter correct details or sign up instead";
    }



}
