package com.postInfo.sys.post.service.user.profile;

import com.postInfo.sys.post.model.Profile;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService{
    Profile save(Profile profile);
}
