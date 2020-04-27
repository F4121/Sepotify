package com.enigma.sepotify.controller;

import com.enigma.sepotify.entity.Profile;
import com.enigma.sepotify.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable String id){
        return profileService.getProfile(id);
    }

    @PostMapping
    public void saveProfile(@RequestBody Profile requestBody){
        profileService.saveProfile(requestBody);
    }

    @DeleteMapping
    public void deleteProfile(@RequestBody Profile profile){
        profileService.deleteProfile(profile.getId());
    }
}
