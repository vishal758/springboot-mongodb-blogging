package com.postInfo.sys.post.controller.user;

import com.postInfo.sys.post.data.response.User.UserData;
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
            UserData userData;
            userData = userService.fillUserData(singleUser);
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
        UserData userData;
        userData = userService.fillUserData(user);
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

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void modifyUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
//        user.setId(id);
//        userService.save(user);
//    }
}
