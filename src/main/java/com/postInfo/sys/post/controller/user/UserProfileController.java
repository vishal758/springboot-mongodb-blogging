package com.postInfo.sys.post.controller.user;

import com.postInfo.sys.post.service.role.RoleService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users/")
public class UserProfileController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserProfileController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


}
