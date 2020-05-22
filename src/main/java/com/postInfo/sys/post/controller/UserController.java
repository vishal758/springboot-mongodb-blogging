package com.postInfo.sys.post.controller;

import com.postInfo.sys.post.model.User;
//import com.postInfo.sys.post.service.user.CustomUserDetailsService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

//    @GetMapping("/{username}")
//    public UserDetails loadUserByUsername(@PathVariable String username) {
//        return customUserDetailsService.loadUserByUsername(username);
//    }

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User getUserById(@PathVariable("id") String id) {
//        return userService.findBy_id(id);
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user) {
        user.setId(user.getId());
        userService.save(user);
//        customUserDetailsService.save(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyUserById(@PathVariable("id") String id, @Valid @RequestBody User user) {
        user.setId(id);
        userService.save(user);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteUser(@PathVariable String id) {
//        userService.delete(userService.findBy_id(id));
//    }
}
