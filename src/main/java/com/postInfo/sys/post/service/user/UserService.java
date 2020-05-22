package com.postInfo.sys.post.service.user;

//import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
//import org.bson.types.String;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    List<User> findAll();

//    User findBy_id(String id);

    User save(User user);

    void delete(User by_id);
}
