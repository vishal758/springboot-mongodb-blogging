package com.postInfo.sys.post.controller;

import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Post> allPosts() {
        return postService.findAllPosts();
    }


//    @DeleteMapping("/allPosts/{id}")
//    public void deletePost(@PathVariable("id") String id) {
////        postService.delete(postService.findBy_id(id));
//        postService.delete(postService.findPostById(id));
//    }
}
