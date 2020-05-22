package com.postInfo.sys.post.service.user;

//import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.repository.UserRepository;
//import org.bson.types.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

//    @Override
//    public User findBy_id(String id) {
//        return userRepository.findBy_id(id);
//    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User by_id) {
        userRepository.delete(by_id);
    }
}
