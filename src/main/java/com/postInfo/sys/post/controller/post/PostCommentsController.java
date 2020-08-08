package com.postInfo.sys.post.controller.post;

import com.postInfo.sys.post.data.response.CommentData;
import com.postInfo.sys.post.data.response.MessageResponse;
import com.postInfo.sys.post.data.response.SuccessResponse;
import com.postInfo.sys.post.model.Comment;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.User;
import com.postInfo.sys.post.service.comments.CommentService;
import com.postInfo.sys.post.service.post.PostService;
import com.postInfo.sys.post.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users/{username}/posts/{postId}/comments")
public class PostCommentsController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public PostCommentsController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getAllCommentsOnPost(@PathVariable String username, @PathVariable String postId) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(postId);

        if(user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        } else if(post == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));

        List<Comment> commentList = commentService.findAllByPostId(postId);
        List<CommentData> commentData = postService.fillCommentData(commentList);

        return ResponseEntity.ok(commentData);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity submitCommentOnPost(@Valid @RequestBody Comment comment, @PathVariable String username, @PathVariable String postId) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(postId);

        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(post == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));

        comment.setPostId(postId);
        commentService.save(comment);

        return ResponseEntity.ok(new SuccessResponse(comment.getId(), "Comment created Successfully"));
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity editCommentOnPost(@Valid @RequestBody Comment comment, @PathVariable String username, @PathVariable String postId,
                                            @PathVariable String commentId) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(postId);
        Comment origComment = commentService.findCommentById(commentId);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(post == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Post not found"));
        else if(origComment.getCommentBy().compareTo(user.getId()) != 0)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authorised to modify this post"));
        else if(origComment == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Comment not found"));
        comment.setId(commentId);
        commentService.save(comment);

        return ResponseEntity.ok(new SuccessResponse(comment.getId(), "Comment updated Successfully"));
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteCommentOnPost(@PathVariable String username, @PathVariable String postId,
                                            @PathVariable String commentId) {
        User user = userService.findUserByUsername(username);
        Post post = postService.findPostById(postId);
        Comment comment = commentService.findCommentById(commentId);
        if(user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        else if(comment.getCommentBy().compareTo(user.getId()) != 0)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authorised to delete that comment"));
        else if(comment == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Comment not found"));

        commentService.delete(comment);

        return ResponseEntity.ok(new SuccessResponse(comment.getId(), "Comment deleted Successfully"));
    }
}
