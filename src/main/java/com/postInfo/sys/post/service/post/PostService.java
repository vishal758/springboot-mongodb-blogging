package com.postInfo.sys.post.service.post;

import com.postInfo.sys.post.data.response.CommentData;
import com.postInfo.sys.post.model.Comment;
import com.postInfo.sys.post.model.Post;


import java.util.List;

public interface PostService {
    List<Post> findAllByUser(String userId);

    List<Post> findAllPosts();
    Post save(Post post);

//    Post findBy_id(String id);
    Post findPostById(String id);

    void delete(Post by_id);

    List<CommentData> fillCommentData(List<Comment> comments);
}
