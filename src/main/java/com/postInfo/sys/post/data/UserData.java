package com.postInfo.sys.post.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.postInfo.sys.post.model.Profile;
import com.postInfo.sys.post.model.Role;

import java.util.Set;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    private String id;
    private String username;
    private String email;
    private Set<Role> role;
    private Profile profile;

    public UserData() {
    }

    public UserData(String id, String username, String email, Set<Role> role, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.profile = profile;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
