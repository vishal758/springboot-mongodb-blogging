package com.postInfo.sys.post.service.role;

import com.postInfo.sys.post.model.Enum.ERole;
import com.postInfo.sys.post.model.Role;


public interface RoleService {
    Role findByName(ERole roleUser);
}
