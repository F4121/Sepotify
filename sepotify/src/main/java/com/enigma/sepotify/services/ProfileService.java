package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    public Profile saveProfile(Profile profile);
    public Profile getProfile(String id);
    public Page<Profile> searchProfile(Pageable pageable, Profile searchForm);
    public void deleteProfile(String id);
}
