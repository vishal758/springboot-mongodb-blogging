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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users/{username}/favPosts")
public class FavPostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public FavPostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getFavPostsOfUser(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if(user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        }

        List<Post> posts = user.getFavPosts();
        if(posts.size() > 0)
            return ResponseEntity.ok(posts);

        return ResponseEntity.ok(new MessageResponse("No Favourite posts from this user."));
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity addOrRemoveFavPost(@PathVariable String postId, @PathVariable String username, @RequestParam("action") String action) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(postId);

        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        List<Post> favPosts = user.getFavPosts();
        ResponseEntity<SuccessResponse> successResponse;
        if(action.compareTo("remove") == 0) {
            for(Post singlePost: favPosts) {
                if(post.getId().compareTo(singlePost.getId()) == 0) {
                    favPosts.remove(singlePost);
                    break;
                }
            }
            successResponse = ResponseEntity.ok(new SuccessResponse(post.getId(), "Post removed from Favourites Successfully"));
        } else if(action.compareTo("add") == 0){
            for(Post singlePost: favPosts) {
                if(post.getId().compareTo(singlePost.getId()) == 0) {
                    return ResponseEntity.ok(new SuccessResponse(post.getId(), "Post is already in Favourites."));
                }
            }
            favPosts.add(post);
            successResponse = ResponseEntity.ok(new SuccessResponse(post.getId(), "Post added to Favourites Successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Not a valid request."));
        }
        user.setFavPosts(favPosts);
        userService.save(user);
        return successResponse;
    }
}
