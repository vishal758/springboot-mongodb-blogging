package com.postInfo.sys.post.service.comments;

import com.postInfo.sys.post.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByPostId(String postId);

    Comment save(Comment comment);

    Comment findCommentById(String commentId);

    void delete(Comment comment);
}
