package com.postInfo.sys.post.repository;


import com.postInfo.sys.post.model.Role;
import com.postInfo.sys.post.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findUserById(String id);

    List<User> findUserByRoles(Role user);
}
