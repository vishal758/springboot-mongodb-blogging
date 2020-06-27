package com.postInfo.sys.post.service.role;

import com.postInfo.sys.post.model.Enum.ERole;
import com.postInfo.sys.post.model.Role;
import com.postInfo.sys.post.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByName(ERole roleUser) {
        return roleRepository.findByName(roleUser);
    }
}
