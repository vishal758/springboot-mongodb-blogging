package com.postInfo.sys.post.controller.post;
import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.service.post.PostService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("users/{username}/posts")
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
    public ResponseEntity getAllPostsByUser(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if(user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        }
        List<Post> posts = postService.findAllByUser(user.getId());
        if(posts.size() == 0)
            return ResponseEntity.ok(new SuccessResponse(username, "No posts from this user"));
        return ResponseEntity.ok(posts);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getPostById(@PathVariable("id") String id, @PathVariable String username) {
        Post post = postService.findPostById(id);
        User user = userService.findUserByUsername(username);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(post == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));
        else if(post.getUserId().compareTo(user.getId()) != 0)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post is not of this user"));

        return ResponseEntity.ok(post);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity createPost(@Valid @RequestBody Post post, @PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        post.setUserId(user.getId());
        post.setAuthor(user.getUsername());
        post.setLastModifiedDate(LocalDateTime.now());
        postService.save(post);
        return ResponseEntity.ok(new SuccessResponse(post.getId(), "Post created Successfully"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modifyPostById(@PathVariable("id") String id, @Valid @RequestBody Post post, @PathVariable String username) {
        User user = userService.findUserByUsername(username);
        Post origPost = postService.findPostById(id);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(origPost == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));
        else if(origPost.getUserId().compareTo(user.getId()) != 0)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authorised to modify this post"));
        post.setId(id);
        post.setUserId(user.getId());
        post.setAuthor(user.getUsername());
        post.setLastModifiedDate(LocalDateTime.now());
        postService.save(post);

        return ResponseEntity.ok(new SuccessResponse(post.getId(), "Post modified Successfully"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deletePost(@PathVariable String id, @PathVariable String username) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(id);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(post == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));
        else if(post.getUserId().compareTo(user.getId()) != 0)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authorised to delete that post"));
        postService.delete(post);
        return ResponseEntity.ok(new SuccessResponse(post.getId(), "Post deleted Successfully"));
    }
}


