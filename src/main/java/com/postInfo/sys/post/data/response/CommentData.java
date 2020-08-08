package com.postInfo.sys.post.data.response;

public class CommentData {
    private String id;
    private String message;
    private String commentBy;

    public CommentData() {
    }

    public CommentData(String id, String message, String commentBy) {
        this.id = id;
        this.message = message;
        this.commentBy = commentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }
}
