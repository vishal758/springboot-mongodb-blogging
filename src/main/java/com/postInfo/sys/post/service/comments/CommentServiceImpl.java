package com.postInfo.sys.post.service.comments;

import com.postInfo.sys.post.model.Comment;
import com.postInfo.sys.post.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAllByPostId(String postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(String commentId) {
        return commentRepository.findCommentById(commentId);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
