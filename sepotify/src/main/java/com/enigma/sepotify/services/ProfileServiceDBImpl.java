package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Profile;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceDBImpl implements ProfileService{

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(String id) {
        Profile profile;
        if (profileRepository.findById(id).isPresent()) profile = profileRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Profile.class);
        return profile;
    }

    @Override
    public Page<Profile> searchProfile(Pageable pageable, Profile searchForm) {
        return null;
    }

    @Override
    public void deleteProfile(String id) {
        if (profileRepository.findById(id).isPresent()) profileRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Profile.class);
    }
}
