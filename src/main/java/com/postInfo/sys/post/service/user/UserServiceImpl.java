package com.postInfo.sys.post.service.user;

import com.postInfo.sys.post.data.response.User.Profile.ContactDetails;
import com.postInfo.sys.post.data.response.User.Profile.SocialProfile;
import com.postInfo.sys.post.data.response.User.UserData;
import com.postInfo.sys.post.data.response.User.Profile.UserProfileData;
import com.postInfo.sys.post.model.Profile;
import com.postInfo.sys.post.model.Role;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User by_id) {
        userRepository.delete(by_id);
    }

    @Override
    public List<User> findUserByRoles(Role user) {
        return userRepository.findUserByRoles(user);
    }

    @Override
    public Profile findProfileById(String id) {
        return userRepository.findProfileById(id);
    }

    @Override
    public User findUserByUsername(String id) {
        return userRepository.findByUsername(id);
    }

    @Override
    public UserData fillUserData(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setRole(user.getRoles());

        if(user.getProfile() != null) {
            Profile profile = new Profile();
            profile.setAddress(user.getProfile().getAddress());
            profile.setPhoneNumber(user.getProfile().getPhoneNumber());
            profile.setLinkedInProfile(user.getProfile().getLinkedInProfile());
            profile.setGithubProfile(user.getProfile().getGithubProfile());

            UserProfileData userProfileData = new UserProfileData();

            ContactDetails contactDetails = new ContactDetails(profile.getPhoneNumber(), profile.getAddress());
            SocialProfile socialProfile = new SocialProfile(profile.getGithubProfile(), profile.getLinkedInProfile());
            userProfileData.setContactDetails(contactDetails);
            userProfileData.setSocialProfile(socialProfile);

            userData.setUserProfileData(userProfileData);
        }

//        if(user.getFavPosts().size() != 0) {
//            userData.setFavPosts(user.getFavPosts());
//        }
        return userData;
    }
}
