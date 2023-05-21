package com.geekster.musicstreamingapi.controller;

import com.geekster.musicstreamingapi.model.Admin;
import com.geekster.musicstreamingapi.model.Song;
import com.geekster.musicstreamingapi.service.AdminService;
import com.geekster.musicstreamingapi.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    SongService songService;

    @PostMapping("/add")
    public String addAdmin(@RequestBody Admin admin)
    {
        return adminService.add(admin);
    }

}
