package com.postInfo.sys.post.service.post;

import com.postInfo.sys.post.data.response.CommentData;
import com.postInfo.sys.post.model.Comment;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAllByUser(String userId) {
        List<Post> posts = new ArrayList<>();
        postRepository.findByUserIdOrderByLastModifiedDateDesc(userId).forEach(posts::add);
        return posts;
    }

    @Override
    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAllByOrderByLastModifiedDateDesc().forEach(posts::add);
        return posts;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }


    @Override
    public Post findPostById(String id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void delete(Post by_id) {
        postRepository.delete(by_id);
    }

    @Override
    public List<CommentData> fillCommentData(List<Comment> comments) {
        List<CommentData> commentData = new ArrayList<>();
        for(Comment comment: comments) {
            CommentData data = new CommentData();
            data.setId(comment.getId());
            data.setMessage(comment.getMessage());
            data.setCommentBy(comment.getCommentBy());
            commentData.add(data);
        }
        return commentData;
    }

    @Override
    public Boolean isFavPostOfUser(User user, String postId) {
        List<Post> posts = user.getFavPosts();
        for(Post post: posts) {
            if(post.getId().compareTo(postId) == 0) {
                return true;
            }
        }
        return false;
    }
}
