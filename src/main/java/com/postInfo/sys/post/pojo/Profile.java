package com.postInfo.sys.post.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profiles")
public class Profile {

    private String githubProfile;
    private String linkedInProfile;

    public Profile(String githubProfile, String linkedInProfile) {
        this.githubProfile = githubProfile;
        this.linkedInProfile = linkedInProfile;
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

    @Override
    public String toString() {
        return "Profiles{" +
                ", githubProfile='" + githubProfile + '\'' +
                ", linkedInProfile='" + linkedInProfile + '\'' +
                '}';
    }
}
