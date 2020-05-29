package com.postInfo.sys.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {
    @Id
    private String id;
    private String githubProfile;
    private String linkedInProfile;
    private String phoneNumber;
    private String address;

    public Profile() {
    }

    public Profile(String id, String githubProfile, String linkedInProfile, String phoneNumber, String address) {
        this.id = id;
        this.githubProfile = githubProfile;
        this.linkedInProfile = linkedInProfile;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGithubProfile() {
        return githubProfile;
    }

    public void setGithubProfile(String githubProfile) {
        this.githubProfile = githubProfile;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
