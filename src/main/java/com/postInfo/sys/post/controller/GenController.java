package com.postInfo.sys.post.controller;

import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenController {

    @Autowired
    private PostService postService;

    @GetMapping("/allPosts")
    public List<Post> allPosts() {
        return postService.findAllPosts();
    }


    @DeleteMapping("/allPosts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        postService.delete(postService.findBy_id(id));
    }
}
