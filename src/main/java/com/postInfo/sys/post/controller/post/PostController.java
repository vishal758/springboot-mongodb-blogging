package com.postInfo.sys.post.controller.post;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.service.post.PostService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("users/{userId}/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Post> getAllPostsByUser(@PathVariable String userId) {
        return postService.findAllByUser(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Post getPostById(@PathVariable("id") String id, @PathVariable String userId) {
        return postService.findPostById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public SuccessResponse createPost(@Valid @RequestBody Post post, @PathVariable String userId) {
        User user = userService.findUserById(userId);
        post.setId(post.getId());
        post.setUserId(userId);
        post.setAuthor(user.getUsername());
        post.setLastModifiedDate(Calendar.getInstance().getTime());
        postService.save(post);
        return new SuccessResponse(post.getId(), "Post created Successfully");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SuccessResponse modifyPostById(@PathVariable("id") String id, @Valid @RequestBody Post post, @PathVariable String userId) {
        User user = userService.findUserById(userId);
        if(user == null)
            throw new RuntimeException("Error: User not found");
        post.setId(id);
        post.setUserId(userId);
        post.setAuthor(user.getUsername());
        post.setLastModifiedDate(Calendar.getInstance().getTime());
        postService.save(post);

        return new SuccessResponse(post.getId(), "Post modified Successfully");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public SuccessResponse deletePost(@PathVariable String id) {
        Post post = postService.findPostById(id);
        postService.delete(post);
        return new SuccessResponse(post.getId(), "Post deleted Successfully");
    }
}


