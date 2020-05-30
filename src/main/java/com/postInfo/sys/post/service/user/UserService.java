package com.postInfo.sys.post.service.user;

import com.postInfo.sys.post.data.response.User.UserData;
import com.postInfo.sys.post.model.Profile;
import com.postInfo.sys.post.model.Role;
import com.postInfo.sys.post.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<User> findAll();

    User findUserById(String id);
//    User findBy_id(String id);

    User save(User user);

    void delete(User by_id);

    List<User> findUserByRoles(Role role);

    Profile findProfileById(String id);

    UserData fillUserData(User user);

    User findUserByUsername(String id);
}
