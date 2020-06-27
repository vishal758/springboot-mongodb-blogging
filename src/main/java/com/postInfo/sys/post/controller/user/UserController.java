package com.postInfo.sys.post.controller.user;

import com.postInfo.sys.post.data.response.User.UserData;
import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.*;
import com.postInfo.sys.post.model.Enum.ERole;
import com.postInfo.sys.post.service.role.RoleService;
import com.postInfo.sys.post.service.user.UserService;
import com.postInfo.sys.post.service.user.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<UserData> users = new ArrayList<>();
        for(final User singleUser: user) {
            UserData userData;
            userData = userService.fillUserData(singleUser);
            users.add(userData);
        }
        if(users.size() == 0) {
            return ResponseEntity.ok(new MessageResponse("No users found"));
        }
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getParticularUserByUserName(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if(user == null )
            return ResponseEntity.badRequest().body(new SuccessResponse(username, "No user found with given username"));
        UserData userData;
        userData = userService.fillUserData(user);
        return ResponseEntity.ok(userData);
    }

    @RequestMapping(value = "/{username}/profile", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateUserProfile(@PathVariable String username,  @Valid @RequestBody Profile profile) {
        User user = userService.findUserByUsername(username);
        if(user == null )
            return ResponseEntity.badRequest().body(new SuccessResponse(username, "No user found with given username"));
        profileService.save(profile);
        user.setProfile(profile);
        userService.save(user);
        return ResponseEntity.ok(new SuccessResponse(username, "Profile section updated successfully for given user."));
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void modifyUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
//        user.setId(id);
//        userService.save(user);
//    }
}
