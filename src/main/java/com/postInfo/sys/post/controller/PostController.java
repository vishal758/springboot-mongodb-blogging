package com.postInfo.sys.post.controller;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.service.post.PostService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("users/{userId}/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Post> getAllPostsByUser(@PathVariable String userId) {
        return postService.findAllByUser(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable("id") String id, @PathVariable String userId) {
        return postService.findBy_id(id);
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public Post createPost(@Valid @RequestBody Post post, @PathVariable String userId) {
//        User user = userService.findBy_id(userId);
//        post.set_id(post.get_id());
//        post.setUserId(userId);
//        post.setAuthor(user.getUserName());
//        post.setLastModifiedDate(Calendar.getInstance().getTime());
//        postService.save(post);
//        return post;
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void modifyPostById(@PathVariable("id") String id, @Valid @RequestBody Post post, @PathVariable String userId) {
//        User user = userService.findBy_id(userId);
//        post.set_id(id);
//        post.setUserId(userId);
//        post.setAuthor(user.getUserName());
//        post.setLastModifiedDate(Calendar.getInstance().getTime());
//        postService.save(post);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id, @PathVariable String userId) {
        postService.delete(postService.findBy_id(id));
    }
}


