package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Profile;
import com.enigma.sepotify.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileServiceDBImplTest {

    @Autowired
    ProfileService profileService;

    @Autowired
    ProfileRepository profileRepository;

    Profile profile = new Profile();

    @BeforeEach
    public void cleanUp(){
        profileRepository.deleteAll();
    }

    @Test
    void saveProfile_should_returnIdNotNull() {
        profile.setFirstName("test");
        profile = profileService.saveProfile(profile);
        assertTrue(profile.getId()!=null);
    }

    @Test
    void saveProfile_should_1_Data_InDB() {
        profile.setFirstName("test");
        profile = profileService.saveProfile(profile);
        assertEquals(1, profileRepository.findAll().size());
    }

    @Test
    void getProfile_should_return_sameFirstName() {
        profile.setFirstName("test");
        profile = profileService.saveProfile(profile);
        Profile expected = profileService.getProfile(profile.getId());
        assertEquals(profile.getFirstName(), expected.getFirstName());
    }

    @Test
    void getAll_Data_InDBShould_2(){
        profile.setFirstName("test");
        profile = profileService.saveProfile(profile);
        Profile profile1 = new Profile();
        profile1.setFirstName("test1");
        profileService.saveProfile(profile1);
        assertEquals(2, profileRepository.findAll().size());
    }

    @Test
    void deleteProfile_should_delete_1_dataInDB() {
        profile.setFirstName("test");
        profile = profileService.saveProfile(profile);
        Profile expected = profileService.getProfile(profile.getId());
        profileService.deleteProfile(profile.getId());
        assertEquals(0, profileRepository.findAll().size());
    }
}