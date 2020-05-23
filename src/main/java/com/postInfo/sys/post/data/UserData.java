package com.postInfo.sys.post.data;

import com.postInfo.sys.post.model.Role;

import java.util.Set;

public class UserData {
    private String id;
    private String username;
    private String email;
    private Set<Role> role;

    public UserData() {
    }

    public UserData(String id, String username, String email, Set<Role> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
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

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
