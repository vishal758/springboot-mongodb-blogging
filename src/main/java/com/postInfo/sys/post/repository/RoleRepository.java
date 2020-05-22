package com.postInfo.sys.post.repository;

import com.postInfo.sys.post.model.ERole;
import com.postInfo.sys.post.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(ERole name);
}
