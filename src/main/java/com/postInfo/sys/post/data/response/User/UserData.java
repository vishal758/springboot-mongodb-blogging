package com.postInfo.sys.post.data.response.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.postInfo.sys.post.data.response.User.Profile.UserProfileData;
import com.postInfo.sys.post.model.Post;
import com.postInfo.sys.post.model.Role;

import java.util.List;
import java.util.Set;
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {
    private String id;
    private String username;
    private String email;
    private Set<Role> role;
//    private List<Post> favPosts;
    private UserProfileData userProfileData;

    public UserData() {
    }

    public UserData(String id, String username, String email, Set<Role> role, List<Post> favPosts, UserProfileData userProfileData) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
//        this.favPosts = favPosts;
        this.userProfileData = userProfileData;
    }

    public UserData(String id, String username, String email, Set<Role> role, UserProfileData userProfileData) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.userProfileData = userProfileData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileData getUserProfileData() {
        return userProfileData;
    }

    public void setUserProfileData(UserProfileData userProfileData) {
        this.userProfileData = userProfileData;
    }

//    public List<Post> getFavPosts() {
//        return favPosts;
//    }
//
//    public void setFavPosts(List<Post> favPosts) {
//        this.favPosts = favPosts;
//    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
