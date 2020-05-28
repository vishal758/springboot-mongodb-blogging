package com.postInfo.sys.post.service.user.profile;

import com.postInfo.sys.post.model.Profile;
import com.postInfo.sys.post.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

//    @Override
//    public Profile findProfileByUserId(String id) {
//        return profileRepository.findProfileByUserId(id);
//    }

//    @Override
//    public Profile findByUserId(String id) {
//        return profileRepository.findByUserId(id);
//    }

//    @Override
//    public Profile findAll() {
//        return null;
//    }
}
