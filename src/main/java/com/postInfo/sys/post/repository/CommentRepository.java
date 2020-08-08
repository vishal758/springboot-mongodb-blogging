package com.postInfo.sys.post.repository;

import com.postInfo.sys.post.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPostId(String postId);

    Comment findCommentById(String commentId);
}
