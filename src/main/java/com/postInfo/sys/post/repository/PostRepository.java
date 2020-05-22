package com.postInfo.sys.post.repository;

import com.postInfo.sys.post.model.Post;
//import org.bson.types.String;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.text.ParsePosition;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Post findBy_id(String id);
    List<Post> findByUserId(String userId);

    List<Post> findAllByOrderByLastModifiedDateDesc();

    List<Post> findByUserIdOrderByLastModifiedDateDesc(String userId);
}
