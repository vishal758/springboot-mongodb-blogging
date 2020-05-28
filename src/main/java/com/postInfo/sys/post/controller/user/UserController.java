package com.postInfo.sys.post.controller.user;

import com.postInfo.sys.post.data.ContactDetails;
import com.postInfo.sys.post.data.SocialProfile;
import com.postInfo.sys.post.data.UserData;
import com.postInfo.sys.post.data.UserProfileData;
import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.*;
import com.postInfo.sys.post.service.role.RoleService;
import com.postInfo.sys.post.service.user.UserService;
import com.postInfo.sys.post.service.user.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private ProfileService profileService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, ProfileService profileService) {
        this.userService = userService;
        this.roleService = roleService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getAllUsers() {
        Role userRole = roleService.findByName(ERole.ROLE_USER);
        List<User> user = userService.findUserByRoles(userRole);
        if(user == null )
            return ResponseEntity.badRequest().body(new MessageResponse("Error: No users found"));
        List<UserData> users = new ArrayList<>();
        for(final User singleUser: user) {
            UserData userData = new UserData();
            userData.setId(singleUser.getId());
            userData.setUsername(singleUser.getUsername());
            userData.setEmail(singleUser.getEmail());
            userData.setRole(singleUser.getRoles());

            if(singleUser.getProfile() != null) {
                Profile profile = new Profile();
                profile.setAddress(singleUser.getProfile().getAddress());
                profile.setPhoneNumber(singleUser.getProfile().getPhoneNumber());
                profile.setLinkedInProfile(singleUser.getProfile().getLinkedInProfile());
                profile.setGithubProfile(singleUser.getProfile().getGithubProfile());

                UserProfileData userProfileData = new UserProfileData();

                ContactDetails contactDetails = new ContactDetails(profile.getPhoneNumber(), profile.getAddress());
                SocialProfile socialProfile = new SocialProfile(profile.getGithubProfile(), profile.getLinkedInProfile());
                userProfileData.setContactDetails(contactDetails);
                userProfileData.setSocialProfile(socialProfile);

                userData.setUserProfileData(userProfileData);
            }

            users.add(userData);
        }
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getParticularUsers(@PathVariable String id) {
        User user = userService.findUserById(id);
        if(user == null )
            return ResponseEntity.badRequest().body(new SuccessResponse(id, "No user found with given id"));

        UserData userData = new UserData();
            userData.setId(user.getId());
            userData.setUsername(user.getUsername());
            userData.setEmail(user.getEmail());
            userData.setRole(user.getRoles());

            if(user.getProfile() != null) {
                Profile profile = new Profile();
                profile.setAddress(user.getProfile().getAddress());
                profile.setPhoneNumber(user.getProfile().getPhoneNumber());
                profile.setLinkedInProfile(user.getProfile().getGithubProfile());
                profile.setGithubProfile(user.getProfile().getGithubProfile());

                UserProfileData userProfileData = new UserProfileData();

                ContactDetails contactDetails = new ContactDetails(profile.getPhoneNumber(), profile.getAddress());
                SocialProfile socialProfile = new SocialProfile(profile.getGithubProfile(), profile.getLinkedInProfile());
                userProfileData.setContactDetails(contactDetails);
                userProfileData.setSocialProfile(socialProfile);

                userData.setUserProfileData(userProfileData);
            }

        return ResponseEntity.ok(userData);
    }


    @RequestMapping(value = "/{id}/profile", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateUserProfile(@PathVariable String id,  @Valid @RequestBody Profile profile) {
        User user = userService.findUserById(id);
        if(user == null )
            return ResponseEntity.badRequest().body(new SuccessResponse(id, "No user found with given id"));

        profileService.save(profile);
        user.setProfile(profile);
        userService.save(user);
        return ResponseEntity.ok(new SuccessResponse(id, "Profile section updated successfully for given user."));
    }
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public User createUser(@Valid @RequestBody User user) {
//        user.setId(user.getId());
//        userService.save(user);
////        customUserDetailsService.save(user);
//        return user;
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void modifyUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
//        user.setId(id);
//        userService.save(user);
//    }


}
