package com.postInfo.sys.post.controller;

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
public class GenController {

    private  PostService postService;
    private UserService userService;

    @Autowired
    public GenController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/allPosts")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity allPosts() {
        List<Post> posts = postService.findAllPosts();
        if(posts == null)
            return ResponseEntity.ok(new MessageResponse("Error: No posts found"));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/allPosts/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity findPostById(@PathVariable String id) {
        Post post = postService.findPostById(id);
        if(post == null)
            return ResponseEntity.ok(new SuccessResponse(id,"No posts found with given id"));
        return ResponseEntity.ok(post);
    }

//    @RequestMapping(value = "users/{username}/posts", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity createPost(@Valid @RequestBody Post post, @PathVariable String username) {
//        User user = userService.findUserByUsername(username);
//        if(user == null)
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
//        post.setUserId(user.getId());
//        post.setAuthor(user.getUsername());
//        post.setLastModifiedDate(LocalDateTime.now());
//        postService.save(post);
//        return ResponseEntity.ok(new SuccessResponse(post.getId(), "Post created Successfully"));
//    }
//    @DeleteMapping("/allPosts/{id}")
//    public void deletePost(@PathVariable("id") String id) {
////        postService.delete(postService.findBy_id(id));
//        postService.delete(postService.findPostById(id));
//    }
}
