package com.postInfo.sys.post.controller;

import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenController {

    private  PostService postService;

    @Autowired
    public GenController(PostService postService) {
        this.postService = postService;
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

//    @DeleteMapping("/allPosts/{id}")
//    public void deletePost(@PathVariable("id") String id) {
////        postService.delete(postService.findBy_id(id));
//        postService.delete(postService.findPostById(id));
//    }
}
