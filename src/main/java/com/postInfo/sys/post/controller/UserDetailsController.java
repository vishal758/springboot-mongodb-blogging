package com.postInfo.sys.post.controller;

import com.postInfo.sys.post.data.UserData;
import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.model.User;
//import com.postInfo.sys.post.service.user.CustomUserDetailsService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userDetails")
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserData> findAll() {
        List<User> user = userService.findAll();
        List<UserData> users = new ArrayList<>();
        for(final User singleUser: user) {
            UserData userData = new UserData();
            userData.setId(singleUser.getId());
            userData.setUsername(singleUser.getUsername());
            userData.setEmail(singleUser.getEmail());
            userData.setRole(singleUser.getRoles());
            users.add(userData);
        }
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public UserData getUserById(@PathVariable("id") String id) {
        User user = userService.findUserById(id);
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setRole(user.getRoles());
        return userData;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User user = userService.findUserById(id);
        userService.delete(user);
        return ResponseEntity
                .ok(new MessageResponse("User with resourceId: " + id + " and userName: " + user.getUsername() + " deleted successfully"));
    }
}
