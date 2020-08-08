package com.postInfo.sys.post.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postInfo.sys.post.data.response.CommentData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "post")
public class Post {
    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String desc;
    private String author;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
    private String userId;

    @DBRef
    private List<CommentData> comments = new ArrayList<>();

    public Post(String id, String title, String desc, String userId, String author, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.userId = userId;
        this.author = author;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CommentData> getComments() {
        return comments;
    }

    public void setComments(List<CommentData> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}